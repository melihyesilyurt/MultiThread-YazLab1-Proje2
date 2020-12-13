package sample.operations;


import sample.ShoppingMall;
import sample.models.Elevator;
import sample.models.Floor;
import sample.models.PersonGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ElevatorTask implements Runnable {
    private List<Floor> floors;
    private ArrayList<PersonGroup> personGroups;
    private Elevator elevator;
    private static int quitCount=0;

    public ElevatorTask(List<Floor> floors , Elevator elevator, ArrayList<PersonGroup> personGroups) {
        this.floors = floors;
        this.elevator = elevator;
        this.personGroups = personGroups;
    }

    @Override
    public void run() {

        for (PersonGroup personGroup: personGroups) {

            floors.get(personGroup.getStartFloor()).getQueue().remove(personGroup);
            try {
                Thread.sleep(200 * Math.abs(personGroup.getEndFloor() - personGroup.getStartFloor() + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            floors.get(personGroup.getEndFloor()).getResidents().add(personGroup);
            System.out.println(
                    "Person Group id "+ personGroup.getId() +" reached to the "
                            + personGroup.getEndFloor()
                            + " from " + personGroup.getStartFloor()
            );

            if(personGroup.getEndFloor()==0)
            {
                quitCount++;
                ShoppingMall.Instance.exitCount.setText(String.valueOf(quitCount));
            }

            personGroup.setStartFloor(personGroup.getEndFloor());
            personGroup.setEndFloor(0);


            this.elevator.setBusy(new AtomicInteger(0));
        }
    }
}
