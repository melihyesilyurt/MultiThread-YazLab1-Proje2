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
            /*    if(i==0)
                {
                    ShoppingMall.Instance.elevator1Active.setText("True");
                }
                else if(i==1)
                {
                    ShoppingMall.Instance.elevator2Active.setText("True");
                }
                else if(i==2)
                {
                    ShoppingMall.Instance.elevator3Active.setText("True");
                }
                else if(i==3)
                {
                    ShoppingMall.Instance.elevator4Active.setText("True");
                }
                else if(i==4)
                {
                    ShoppingMall.Instance.elevator5Active.setText("True");
                }*/
                return i;
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
                    ShoppingMall.Instance.floor0Queue.setText(String.valueOf(floors.get(0).getQueue().size()));
                    ShoppingMall.Instance.floor1Queue.setText(String.valueOf(floors.get(1).getQueue().size()));
                    ShoppingMall.Instance.floor2Queue.setText(String.valueOf(floors.get(2).getQueue().size()));
                    ShoppingMall.Instance.floor3Queue.setText(String.valueOf(floors.get(3).getQueue().size()));
                    ShoppingMall.Instance.floor4Queue.setText(String.valueOf(floors.get(4).getQueue().size()));
                    ShoppingMall.Instance.floor1All.setText(String.valueOf(floors.get(1).getResidents().size()+floors.get(1).getQueue().size()));
                    ShoppingMall.Instance.floor2All.setText(String.valueOf(floors.get(2).getResidents().size()+floors.get(2).getQueue().size()));
                    ShoppingMall.Instance.floor3All.setText(String.valueOf(floors.get(3).getResidents().size()+floors.get(3).getQueue().size()));
                    ShoppingMall.Instance.floor4All.setText(String.valueOf(floors.get(4).getResidents().size()+floors.get(4).getQueue().size()));
                    if(availableElevators.get(0)==1){
                        ShoppingMall.Instance.elevator1Active.setText("True");
                    }
                    else{
                        ShoppingMall.Instance.elevator1Active.setText("False");
                    }
                    if(availableElevators.get(1)==1){
                        ShoppingMall.Instance.elevator2Active.setText("True");
                    }
                    else{
                        ShoppingMall.Instance.elevator2Active.setText("False");
                    }
                    if(availableElevators.get(2)==1){
                        ShoppingMall.Instance.elevator3Active.setText("True");
                    }
                    else{
                        ShoppingMall.Instance.elevator3Active.setText("False");
                    }
                    if(availableElevators.get(3)==1){
                        ShoppingMall.Instance.elevator4Active.setText("True");
                    }
                    else{
                        ShoppingMall.Instance.elevator4Active.setText("False");
                    }
                    if(availableElevators.get(4)==1){
                        ShoppingMall.Instance.elevator5Active.setText("True");
                    }
                    else{
                        ShoppingMall.Instance.elevator5Active.setText("False");
                    }

                    Elevator elevator = this.findNextAvailableElevator();
                    int groupSize = Math.min(floor.getQueue().size(), 5);
                    if (floor.getQueue().size() > 0 && elevator != null) {
                        ArrayList<PersonGroup> groups = new ArrayList<>();
                        for (int i = 0; i < groupSize; i++) {
                            groups.add((PersonGroup) floor.getQueue().take());
                          /*  if(floor.getName().equals("Floor 0") )
                            {
                                ShoppingMall.Instance.floor0Groups.setText("amk barışı");
                            }*/
                        }
                      //  ShoppingMall.Instance.floor0Groups.setText("");
                        elevator.getExecutorService().submit(new ElevatorTask(floors, elevator, groups));
                    }
                    if ((floor.getQueue().size() > 20) && (this.elevators.size() < 5)) {//hata var büyük ihtimalle elevator yerine floor olucak
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
                        elevators.add(new Elevator(te, findNextAvailableElevatorId()));
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    Elevator lastElevator = elevators.get(elevators.size()-1);
//                    lastElevator.getExecutorService().shutdown();
//                    elevators.remove(lastElevator);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
