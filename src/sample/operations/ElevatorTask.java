package sample.operations;


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
            this.elevator.setBusy(new AtomicInteger(0));
        }
    }
}
