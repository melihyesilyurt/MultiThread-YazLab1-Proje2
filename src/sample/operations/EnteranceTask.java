package sample.operations;

import sample.ShoppingMall;
import sample.models.Floor;
import sample.models.PersonGroup;

import java.util.Queue;
import java.util.Random;

public class EnteranceTask implements Runnable {
    private Floor baseFloor;
    private Random random;
    private static int idCounter = 0;
    int groupSize=0;
    int group2=0;
    private  PersonGroup people;
    public EnteranceTask(Floor baseFloor) {
        this.baseFloor = baseFloor;
        this.random = new Random();
    }
    @Override
    public void run() {
        try {
            while (true) {
                idCounter += 1;
                if(groupSize==0)
                {
                    groupSize = random.nextInt(10)+1;
                    group2=groupSize;
                    Thread.sleep(500);
                }
                int destinationFloor = random.nextInt(4) + 1;
                PersonGroup personGroup = new PersonGroup(idCounter, 0, destinationFloor,groupSize);
                System.out.println("Group size: "+ group2 +" and id: #"+ idCounter + " has arrived to shopping mall");
                baseFloor.getQueue().add(personGroup);
               groupSize--;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
