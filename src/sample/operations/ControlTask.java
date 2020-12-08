package sample.operations;

import sample.ShoppingMall;
import sample.models.Elevator;
import sample.models.Floor;
import sample.models.PersonGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class ControlTask implements Runnable {
    private List<Floor> floors;
    private ArrayList<Elevator> elevators;
    private AtomicIntegerArray availableElevators = new AtomicIntegerArray(new int[]{1,0,0,0,0});
    public ControlTask(List<Floor> floors, ArrayList<Elevator> elevators) {
        this.floors = floors;
        this.elevators = elevators;
    }

    private int findNextAvailableElevatorId() {
        for(int i = 0; i< availableElevators.length(); i++) {

            if (availableElevators.get(i) == 0){

                return i;
            }
            else{

            }
        }
        return -1;

    }

    private Elevator findNextAvailableElevator() {
        for(int i = 0; i< elevators.size(); i++) {
            if (elevators.get(i).getBusy() == 0){
                return elevators.get(i);
            }
        }
        return null;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (Floor floor : floors) {
                    Elevator elevator = this.findNextAvailableElevator();
                    int groupSize = Math.min(floor.getQueue().size(), 5);
                    if (floor.getQueue().size() > 0 && elevator != null) {
                        ArrayList<PersonGroup> groups = new ArrayList<>();
                        for (int i = 0; i < groupSize; i++) {
                            groups.add((PersonGroup) floor.getQueue().take());
                        }
                        elevator.getExecutorService().submit(new ElevatorTask(floors, elevator, groups));
                    }
                    if ((floor.getQueue().size() > 20) && (this.elevators.size() < 5)) {
                        // Create a new elevator
                        System.out.println("New elevator id "+findNextAvailableElevatorId() +" activated");
                        ExecutorService te = newFixedThreadPool(1, new ThreadFactory() {
                            @Override
                            public Thread newThread(Runnable r) {
                                String name = "Elevator - " + findNextAvailableElevatorId();
                                return new Thread(r, name);
                            }
                        });
                        availableElevators.getAndSet(findNextAvailableElevatorId(), 1);
                        elevators.add(new Elevator(te));
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}