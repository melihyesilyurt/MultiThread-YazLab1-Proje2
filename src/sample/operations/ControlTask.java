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
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class ControlTask implements Runnable {
    private List<Floor> floors;
    private ArrayList<Elevator> elevators;
    private AtomicIntegerArray availableElevators = new AtomicIntegerArray(new int[]{1,0,0,0,0});
    int allCountQueue;
    public ControlTask(List<Floor> floors, ArrayList<Elevator> elevators) {
        this.floors = floors;
        this.elevators = elevators;
    }
//    [1, 0, 1, 0, 0]
    private int findNextAvailableElevatorId() {
        for(int i = 0; i< availableElevators.length(); i++) {
            if (availableElevators.get(i) == 0){
                return i;
            }
        }
        return -1;
    }

    private Elevator findNextAvailableElevator() {
        for(int i = 0; i< elevators.size(); i++) {
            if (((ThreadPoolExecutor) elevators.get(i).getExecutorService()).getQueue().size() == 0){
                return elevators.get(i);
            }
        }
        return null;
    }

    @Override
    public void run() {
        try {
            while (true) {
                allCountQueue=floors.get(0).getQueue().size()+floors.get(1).getQueue().size()+floors.get(2).getQueue().size()+floors.get(3).getQueue().size()+floors.get(4).getQueue().size();
                ShoppingMall.Instance.setAll((floors.get(1).getResidents().size()+floors.get(1).getQueue().size()),(floors.get(2).getResidents().size()+floors.get(2).getQueue().size()),(floors.get(3).getResidents().size()+floors.get(3).getQueue().size()),(floors.get(4).getResidents().size()+floors.get(4).getQueue().size()),floors.get(0).getQueue().size(),floors.get(1).getQueue().size(),floors.get(2).getQueue().size(),floors.get(3).getQueue().size(),floors.get(4).getQueue().size(),availableElevators);
                for (Floor floor : floors) {
                  //  ShoppingMall.Instance.setAll((floors.get(1).getResidents().size()+floors.get(1).getQueue().size()),(floors.get(2).getResidents().size()+floors.get(2).getQueue().size()),(floors.get(3).getResidents().size()+floors.get(3).getQueue().size()),(floors.get(4).getResidents().size()+floors.get(4).getQueue().size()),floors.get(0).getQueue().size(),floors.get(1).getQueue().size(),floors.get(2).getQueue().size(),floors.get(3).getQueue().size(),floors.get(4).getQueue().size(),availableElevators);
                    Elevator elevator = this.findNextAvailableElevator();
                    int groupSize = Math.min(floor.getQueue().size(), 10);//kişi sayısı buradan
                    if (floor.getQueue().size() > 0 && elevator != null) {
                        ArrayList<PersonGroup> groups = new ArrayList<>();
                        for (int i = 0; i < groupSize; i++) {
                            groups.add((PersonGroup) floor.getQueue().take());
                        }
                        elevator.getExecutorService().submit(new ElevatorTask(floors, elevator, groups));
                    }
                    if ((allCountQueue < 10) && (this.elevators.size() > 1)) {
                        Elevator lastElevator = elevators.get(elevators.size()-1);
                        System.out.println("Elevator id "+lastElevator.getId() +" closed");
                      lastElevator.getExecutorService().shutdown();
                    elevators.remove(lastElevator);
                    }
                    if ((floor.getQueue().size() > 20) && (this.elevators.size() < 5)) {
                        // Create a new elevator
                        int availableElevatorId = findNextAvailableElevatorId();
                        System.out.println("New elevator id "+availableElevatorId +" activated");
                        ExecutorService te = newFixedThreadPool(1, new ThreadFactory() {
                            @Override
                            public Thread newThread(Runnable r) {
                                String name = "Elevator - " + availableElevatorId;
                                return new Thread(r, name);
                            }
                        });
                        availableElevators.getAndSet(availableElevatorId, 1);
                        elevators.add(new Elevator(te, availableElevatorId));
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
