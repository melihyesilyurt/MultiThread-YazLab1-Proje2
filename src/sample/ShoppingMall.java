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
    @FXML
    private TextField floor1All;

    @FXML
    private TextField floor2All;

    @FXML
    private TextField floor3All;

    @FXML
    private TextField floor4All;

    @FXML
    private TextField floor4Queue;

    @FXML
    private TextField floor3Queue;

    @FXML
    private TextField floor0Queue;

    @FXML
    private TextField floor1Queue;

    @FXML
    private TextField floor2Queue;

    @FXML
    private Button StartButton;

    @FXML
    private TextField exitCount;

    @FXML
    private TextField floor0Groups;

    @FXML
    private TextField floor1Groups;

    @FXML
    private TextField floor2Groups;

    @FXML
    private TextField floor3Groups;

    @FXML
    private TextField floor4Groups;

    @FXML
    private  TextField elevator1Active;
    @FXML
    private TextField elevator1Floor;

    @FXML
    private TextField elevator1Destination;

    @FXML
    private TextField elevator1Direction;

    @FXML
    private TextField elevator1Capacity;

    @FXML
    private TextField elevator1CountInside;

    @FXML
    private TextField elevator1Inside;

    @FXML
    private TextField elevator2Active;

    @FXML
    private TextField elevator2Floor;

    @FXML
    private TextField elevator2Destination;

    @FXML
    private TextField elevator2Direction;

    @FXML
    private TextField elevator2Capacity;

    @FXML
    private TextField elevator2CountInside;

    @FXML
    private TextField elevator2Inside;

    @FXML
    private TextField elevator3Active;

    @FXML
    private TextField elevator3Floor;

    @FXML
    private TextField elevator3Destination;

    @FXML
    private TextField elevator3Direction;

    @FXML
    private TextField elevator3Capacity;

    @FXML
    private TextField elevator3CountInside;

    @FXML
    private TextField elevator3Inside;

    @FXML
    private TextField elevator4Active;

    @FXML
    private TextField elevator4Floor;

    @FXML
    private TextField elevator4Destination;

    @FXML
    private TextField elevator4Direction;

    @FXML
    private TextField elevator4Capacity;

    @FXML
    private TextField elevator4CountInside;

    @FXML
    private TextField elevator4Inside;

    @FXML
    private TextField elevator5Active;

    @FXML
    private TextField elevator5Floor;

    @FXML
    private TextField elevator5Destination;

    @FXML
    private TextField elevator5Direction;

    @FXML
    private TextField elevator5Capacity;

    @FXML
    private TextField elevator5CountInside;

    @FXML
    private TextField elevator5Inside;

    @FXML
    private   TextField elevator1Mode;

    @FXML
    private  TextField elevator2Mode;

    @FXML
    private  TextField elevator3Mode;

    @FXML
    private  TextField elevator4Mode;

    @FXML
    private  TextField elevator5Mode;
   private void setElevator5Active(String status)
    {
        elevator5Active.setText(status);
    }
    @FXML
    void Run(ActionEvent event) {
        elevator1Active.setText("True");
        elevator2Active.setText("False");
        elevator3Active.setText("False");
        elevator4Active.setText("False");
        elevator5Active.setText("False");
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
        elevators.add(new Elevator(te));
        System.out.println("ShoppingMall was created");
        new Thread(new ControlTask(floors, elevators)).start();
        System.out.println("Control Task has been started");
        new Thread(new EnteranceTask(floors.get(0))).start();
        System.out.println("Entrance Task has been started");
        new Thread(new ExitTask(floors)).start();
        System.out.println("Exit Task has been started");
    }

}
