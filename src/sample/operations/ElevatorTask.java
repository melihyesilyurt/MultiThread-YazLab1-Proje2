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
    private int elevatorCount;
    private static int quitCount=0;

    public ElevatorTask(List<Floor> floors , Elevator elevator, ArrayList<PersonGroup> personGroups) {
        this.floors = floors;
        this.elevator = elevator;
        this.personGroups = personGroups;
    }

    @Override
    public void run() {
        elevatorCount=0;
        for (PersonGroup personGroup: personGroups) {
            elevatorCount++;
        }
        for (PersonGroup personGroup: personGroups) {
            floors.get(personGroup.getStartFloor()).getQueue().remove(personGroup);
            try {
                if(this.elevator.getId()==0)
                {
                    ShoppingMall.Instance.elevator0(personGroup.getEndFloor(),elevatorCount);
                    if (personGroup.getEndFloor() - personGroup.getStartFloor()>0)
                    {
                        ShoppingMall.Instance.elevator1Direction.setText("Up");
                    }
                    else {
                        ShoppingMall.Instance.elevator1Direction.setText("Down");
                    }
                }
                else if(this.elevator.getId()==1)
                {
                    ShoppingMall.Instance.elevator1(personGroup.getEndFloor(),elevatorCount);
                    if (personGroup.getEndFloor() - personGroup.getStartFloor()>0)
                    {
                        ShoppingMall.Instance.elevator2Direction.setText("Up");
                    }
                    else {
                        ShoppingMall.Instance.elevator2Direction.setText("Down");
                    }
                }
                else if(this.elevator.getId()==2)
                {
                    ShoppingMall.Instance.elevator2(personGroup.getEndFloor(),elevatorCount);
                    if (personGroup.getEndFloor() - personGroup.getStartFloor()>0)
                    {
                        ShoppingMall.Instance.elevator3Direction.setText("Up");
                    }
                    else {
                        ShoppingMall.Instance.elevator3Direction.setText("Down");
                    }
                }
                else if(this.elevator.getId()==3)
                {
                    ShoppingMall.Instance.elevator3(personGroup.getEndFloor(),elevatorCount);
                    if (personGroup.getEndFloor() - personGroup.getStartFloor()>0)
                    {
                        ShoppingMall.Instance.elevator4Direction.setText("Up");
                    }
                    else {
                        ShoppingMall.Instance.elevator4Direction.setText("Down");
                    }
                }
                else if(this.elevator.getId()==4)
                {
                    ShoppingMall.Instance.elevator4(personGroup.getEndFloor(),elevatorCount);
                    if (personGroup.getEndFloor() - personGroup.getStartFloor()>0)
                    {
                        ShoppingMall.Instance.elevator5Direction.setText("Up");
                    }
                    else {
                        ShoppingMall.Instance.elevator5Direction.setText("Down");
                    }
                }
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
            if(this.elevator.getId()==0)
            {
                ShoppingMall.Instance.elevator1Floor.setText(String.valueOf(personGroup.getEndFloor()));
                ShoppingMall.Instance.elevator1Mode.setText("idle");
            }
            else if(this.elevator.getId()==1)
            {
                ShoppingMall.Instance.elevator2Floor.setText(String.valueOf(personGroup.getEndFloor()));
                ShoppingMall.Instance.elevator2Mode.setText("idle");
            }
            else if(this.elevator.getId()==2)
            {
                ShoppingMall.Instance.elevator3Floor.setText(String.valueOf(personGroup.getEndFloor()));
                ShoppingMall.Instance.elevator3Mode.setText("idle");
            }
            else if(this.elevator.getId()==3)
            {
                ShoppingMall.Instance.elevator4Floor.setText(String.valueOf(personGroup.getEndFloor()));
                ShoppingMall.Instance.elevator4Mode.setText("idle");
            }
            else if(this.elevator.getId()==4)
            {
                ShoppingMall.Instance.elevator5Floor.setText(String.valueOf(personGroup.getEndFloor()));
                ShoppingMall.Instance.elevator5Mode.setText("idle");
            }
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
