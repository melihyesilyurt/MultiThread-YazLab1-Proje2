package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.models.Elevator;
import sample.models.Floor;
import sample.operations.ControlTask;
import sample.operations.EnteranceTask;
import sample.operations.ExitTask;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.newFixedThreadPool;

public  class ShoppingMall {
    public static ShoppingMall Instance;
    @FXML
    public TextField floor1All;

    @FXML
    public TextField floor2All;

    @FXML
    public TextField floor3All;

    @FXML
    public TextField floor4All;

    @FXML
    public TextField floor4Queue;

    @FXML
    public TextField floor3Queue;

    @FXML
    public TextField floor0Queue;

    @FXML
    public TextField floor1Queue;

    @FXML
    public TextField floor2Queue;

    @FXML
    public Button StartButton;

    @FXML
    public TextField exitCount;

    @FXML
    public TextField floor0Groups;

    @FXML
    public TextField floor1Groups;

    @FXML
    public TextField floor2Groups;

    @FXML
    public TextField floor3Groups;

    @FXML
    public TextField floor4Groups;

    @FXML
    public  TextField elevator1Active;
    @FXML
    public TextField elevator1Floor;

    @FXML
    public TextField elevator1Destination;

    @FXML
    public TextField elevator1Direction;

    @FXML
    public TextField elevator1Capacity;

    @FXML
    public TextField elevator1CountInside;

    @FXML
    public TextField elevator1Inside;

    @FXML
    public TextField elevator2Active;

    @FXML
    public TextField elevator2Floor;

    @FXML
    public TextField elevator2Destination;

    @FXML
    public TextField elevator2Direction;

    @FXML
    public TextField elevator2Capacity;

    @FXML
    public TextField elevator2CountInside;

    @FXML
    public TextField elevator2Inside;

    @FXML
    public TextField elevator3Active;

    @FXML
    public TextField elevator3Floor;

    @FXML
    public TextField elevator3Destination;

    @FXML
    public TextField elevator3Direction;

    @FXML
    public TextField elevator3Capacity;

    @FXML
    public TextField elevator3CountInside;

    @FXML
    public TextField elevator3Inside;

    @FXML
    public TextField elevator4Active;

    @FXML
    public TextField elevator4Floor;

    @FXML
    public TextField elevator4Destination;

    @FXML
    public TextField elevator4Direction;

    @FXML
    public TextField elevator4Capacity;

    @FXML
    public TextField elevator4CountInside;

    @FXML
    public TextField elevator4Inside;

    @FXML
    public TextField elevator5Active;

    @FXML
    public TextField elevator5Floor;

    @FXML
    public TextField elevator5Destination;

    @FXML
    public TextField elevator5Direction;

    @FXML
    public TextField elevator5Capacity;

    @FXML
    public TextField elevator5CountInside;

    @FXML
    public TextField elevator5Inside;

    @FXML
    public   TextField elevator1Mode;

    @FXML
    public  TextField elevator2Mode;

    @FXML
    public  TextField elevator3Mode;

    @FXML
    public  TextField elevator4Mode;

    @FXML
    public  TextField elevator5Mode;
    @FXML
    void Run(ActionEvent event) throws InterruptedException {
        Instance = this;
        List<Floor> floors = Collections.synchronizedList(new ArrayList<Floor>());
        for (int i = 0; i<5; i++) {
            floors.add(new Floor("Floor "+i));
        }
        ArrayList<Elevator> elevators = new ArrayList<>();
        ExecutorService te = newFixedThreadPool(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "Elevator 0");
            }
        });
        elevators.add(new Elevator(te, 0 ));
        System.out.println("ShoppingMall was created");
        new Thread(new ControlTask(floors, elevators)).start();
        System.out.println("Control Task has been started");
        new Thread(new EnteranceTask(floors.get(0))).start();
        System.out.println("Entrance Task has been started");
        new Thread(new ExitTask(floors)).start();
        System.out.println("Exit Task has been started");
    }

}
