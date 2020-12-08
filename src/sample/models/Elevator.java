package sample.models;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Elevator {
    AtomicInteger busy;

    private ExecutorService executorService;

    public Elevator(ExecutorService executorService) {
        this.executorService = executorService;
        this.busy = new AtomicInteger(0);
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
