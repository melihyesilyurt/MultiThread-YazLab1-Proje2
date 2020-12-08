package sample.operations;

import sample.models.Floor;
import sample.models.PersonGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExitTask implements Runnable {

    private List<Floor> floors;
    private Random random;
    public ExitTask(List<Floor> floors) {
        this.floors = floors;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                int randomFloorIndex = random.nextInt(5);
                int groupSize = random.nextInt(5) + 1;
                int destinationFloor = 0;
                ArrayList<Integer> ids = new ArrayList<>();
                Floor selectedFloor = floors.get(randomFloorIndex);
                if (selectedFloor.getResidents().size() > 0) {
                    int howMany = Math.min(selectedFloor.getResidents().size(), groupSize);
                    for (int i = 0; i< howMany; i++) {
                        PersonGroup pg = selectedFloor.getResidents().remove(0);
                        ids.add(pg.getId());
                        selectedFloor.getQueue().add(pg);
                    }
                    System.out.println(Integer.toString(howMany) + " group "+ (Arrays.toString(ids.stream().mapToInt(i->i).toArray())) +" was loaded to the " + selectedFloor.getName() +" 's queue");
                }
                Thread.sleep(1000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
