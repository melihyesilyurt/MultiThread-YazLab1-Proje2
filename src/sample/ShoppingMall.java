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
import java.util.concurrent.atomic.AtomicIntegerArray;

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
       // elevators.add(new Elevator( 0 ));
        System.out.println("ShoppingMall was created");
        new Thread(new ControlTask(floors, elevators)).start();
        System.out.println("Control Task has been started");
        new Thread(new EnteranceTask(floors.get(0))).start();
        System.out.println("Entrance Task has been started");
        new Thread(new ExitTask(floors)).start();
        System.out.println("Exit Task has been started");
    }
    public void setQueue(int floor0QueueInt,int floor1QueueInt,int floor2QueueInt,int floor3QueueInt,int floor4QueueInt)
    {
        floor0Queue.setText(String.valueOf(floor0QueueInt));
        floor1Queue.setText(String.valueOf(floor1QueueInt));
        floor2Queue.setText(String.valueOf(floor2QueueInt));
        floor3Queue.setText(String.valueOf(floor3QueueInt));
        floor4Queue.setText(String.valueOf(floor4QueueInt));
    }
    public void setAll(int floor1AllInt,int floor2AllInt,int floor3AllInt,int floor4AllInt)
    {
        floor1All.setText(String.valueOf(floor1AllInt));
        floor2All.setText(String.valueOf(floor2AllInt));
        floor3All.setText(String.valueOf(floor3AllInt));
        floor4All.setText(String.valueOf(floor4AllInt));
    }
    public void elevator0(int destination, int count)
    {
      elevator1Destination.setText(String.valueOf(destination));
      elevator1CountInside.setText(String.valueOf(count));
    }
    public void elevator1(int destination, int count)
    {
        elevator2Destination.setText(String.valueOf(destination));
        elevator2CountInside.setText(String.valueOf(count));
    }
    public void elevator2(int destination, int count)
    {
        elevator3Destination.setText(String.valueOf(destination));
        elevator3CountInside.setText(String.valueOf(count));
    }
    public void elevator3(int destination, int count)
    {
        elevator4Destination.setText(String.valueOf(destination));
        elevator4CountInside.setText(String.valueOf(count));
    }
    public void elevator4(int destination, int count)
    {
        elevator5Destination.setText(String.valueOf(destination));
        elevator5CountInside.setText(String.valueOf(count));
    }
    public void setControlTaskIdle(AtomicIntegerArray availableElevators )
    {
        if(availableElevators.get(0)==1){
           elevator1Active.setText("True");
           elevator1Mode.setText("Working");
        }
        else{
            elevator1Active.setText("False");
            elevator1Mode.setText("idle");
        }
        if(availableElevators.get(1)==1){
            elevator2Active.setText("True");
            elevator2Mode.setText("Working");
        }
        else{
           elevator2Active.setText("False");
          elevator2Mode.setText("idle");
        }
        if(availableElevators.get(2)==1){
           elevator3Active.setText("True");
            elevator3Mode.setText("Working");
        }
        else{
            elevator3Active.setText("False");
           elevator3Mode.setText("idle");
        }
        if(availableElevators.get(3)==1){
            elevator4Active.setText("True");
           elevator4Mode.setText("Working");
        }
        else{
           elevator4Active.setText("False");
         elevator4Mode.setText("idle");
        }
        if(availableElevators.get(4)==1){
          elevator5Active.setText("True");
            elevator5Mode.setText("Working");
        }
        else{
            elevator5Active.setText("False");
            elevator5Mode.setText("idle");
        }
    }
    public void setGroups(int elevator,int men,int destination)
    {
        if(elevator==0)
        {
          elevator1Inside.setText(elevator1Inside.getText()+"("+men+","+destination+")"+",");
        }
        else if(elevator==1)
        {
           elevator2Inside.setText(elevator2Inside.getText()+"("+men+","+destination+")"+",");
        }
        else if(elevator==2)
        {
           elevator3Inside.setText(elevator3Inside.getText()+"("+men+","+destination+")"+",");
        }
        else if(elevator==3)
        {
            elevator4Inside.setText(elevator4Inside.getText()+"("+men+","+destination+")"+",");
        }
        else if(elevator==4)
        {
           elevator5Inside.setText(elevator5Inside.getText()+"("+men+","+destination+")"+",");
        }
    }
    public void setOpen()
    {
      elevator1Inside.setText("[");
      elevator2Inside.setText("[");
        elevator3Inside.setText("[");
       elevator4Inside.setText("[");
       elevator5Inside.setText("[");
    }
    public void setClose()
    {
       elevator1Inside.setText(elevator1Inside.getText()+"]");
        elevator2Inside.setText(elevator2Inside.getText()+"]");
        elevator3Inside.setText(elevator3Inside.getText()+"]");
       elevator4Inside.setText(elevator4Inside.getText()+"]");
        elevator5Inside.setText(elevator5Inside.getText()+"]");
    }
}
