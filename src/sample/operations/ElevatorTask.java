package sample.operations;


import sample.ShoppingMall;
import sample.models.Elevator;
import sample.models.Floor;
import sample.models.PersonGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class ElevatorTask implements Runnable {
    private List<Floor> floors;
    private ArrayList<PersonGroup> personGroups;
    private Elevator elevator;
    private int elevatorCount;
    private static int quitCount=0;
    private int group=0;
    private int groupInMen=0;
    private  int groupDestination=0;

    public ElevatorTask(List<Floor> floors , Elevator elevator, ArrayList<PersonGroup> personGroups) {
        this.floors = floors;
        this.elevator = elevator;
        this.personGroups = personGroups;
    }

    @Override
    public void run() {
        this.elevator.setBusy(new AtomicInteger(1));
        elevatorCount=0;
        for (PersonGroup personGroup: personGroups) {
            elevatorCount++;
            groupDestination=personGroup.getEndFloor();
            if(personGroup.getGroupSize()==group)
            {
                groupInMen++;
            }
            else
            {
                if(group!=0)
                {
                    ShoppingMall.Instance.setGroups(this.elevator.getId(),groupInMen,groupDestination);
                }
                group=personGroup.getGroupSize();
                groupInMen=1;
            }
        }
        for (PersonGroup personGroup: personGroups) {
            floors.get(personGroup.getStartFloor()).getQueue().remove(personGroup);
            try {
                ShoppingMall.Instance.setElevatorVariables(personGroup.getEndFloor(),elevatorCount,personGroup.getStartFloor(),this.elevator.getId(),quitCount);
            floors.get(personGroup.getEndFloor()).getResidents().add(personGroup);


            System.out.println(
                    "Elevator id: "+this.elevator.getId() +" Person Group id "+ personGroup.getId() +" reached to the "
                            + personGroup.getEndFloor()
                            + " from " + personGroup.getStartFloor()
            );

            if(personGroup.getEndFloor()==0)
            {
                quitCount++;
               // ShoppingMall.Instance.exitCount.setText(String.valueOf(quitCount));
            }
                this.elevator.setBusy(new AtomicInteger(0));
                Thread.sleep(200 * Math.abs(personGroup.getEndFloor() - personGroup.getStartFloor() + 1));
                ShoppingMall.Instance.setDestination(personGroup.getEndFloor(),this.elevator.getId());
            personGroup.setStartFloor(personGroup.getEndFloor());
            personGroup.setEndFloor(0);
           // this.elevator.setBusy(new AtomicInteger(0));
               // Thread.sleep(200 * Math.abs(personGroup.getEndFloor() - personGroup.getStartFloor() + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
