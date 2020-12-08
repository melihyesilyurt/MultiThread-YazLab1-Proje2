package sample.operations;

import sample.models.Floor;
import sample.models.PersonGroup;

import java.util.Random;

public class EnteranceTask implements Runnable {
    private Floor baseFloor;
    private Random random;
    private static int idCounter = 0;
    public EnteranceTask(Floor baseFloor) {
        this.baseFloor = baseFloor;
        this.random = new Random();
    }
    @Override
    public void run() {
        try {
            while (true) {
                idCounter += 1;
                int groupSize = random.nextInt(10)+1;
                int destinationFloor = random.nextInt(4) + 1;
                PersonGroup personGroup = new PersonGroup(idCounter, 0, destinationFloor);
                System.out.println("Group size: "+ groupSize +" and id: #"+ idCounter + " has arrived to shopping mall");
                baseFloor.getQueue().add(personGroup);
                Thread.sleep(500);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
