package sample.models;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Elevator {
    AtomicInteger busy;
    private ExecutorService executorService;
    private int id;

    public Elevator(ExecutorService executorService, int id) {
        this.executorService = executorService;
        this.busy = new AtomicInteger(0);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public  int getBusy() {
        return busy.intValue();
    }

    public  void setBusy(AtomicInteger busy) {
        this.busy = busy;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }
}
