package sample.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Floor {
    private String name;
    private List<PersonGroup> residents =  Collections.synchronizedList(new ArrayList<>());

    private BlockingQueue queue;
    public Floor(String name) {
        queue = new ArrayBlockingQueue(1024);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BlockingQueue getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue queue) {
        this.queue = queue;
    }

    public List<PersonGroup> getResidents() {
        return residents;
    }

    public void setResidents(List<PersonGroup> residents) {
        this.residents = residents;
    }
}
