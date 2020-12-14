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
    private int group=0;
    private int groupInMen=0;
    private  int groupDestination=0;
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
                ShoppingMall.Instance.floor0Groups.setText("[");
              /*  for(int i=0;i<baseFloor.getQueue().size();i++)
                {
                    people=   get(baseFloor.getQueue(),i);
                    if( people.getGroupSize()==group)
                    {
                        groupInMen++;
                    }
                    else
                    {
                        if(group!=0)
                        {
                                ShoppingMall.Instance.floor0Groups.setText(ShoppingMall.Instance.floor0Groups.getText()+"("+groupInMen+","+groupDestination+")"+",");
                        }
                        group=people.getGroupSize();
                        groupInMen=1;
                    }
                }*/
                System.out.println("Group size: "+ group2 +" and id: #"+ idCounter + " has arrived to shopping mall");
                baseFloor.getQueue().add(personGroup);
               groupSize--;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static <T> PersonGroup get(Queue<T> queue, int index) {
        synchronized (queue) {
            if (queue == null) {
                return null;
            }

            int size = queue.size();
            if (index < 0 || size < index + 1) {
                return null;
            }

            PersonGroup element = null;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    element = (PersonGroup) queue.remove();
                } else {
                    queue.add(queue.remove());
                }
            }

            return element;
        }
    }
}
