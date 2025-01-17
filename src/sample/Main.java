package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage Stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage.setTitle("Mall Elevator");
        Stage.setScene(new Scene(root, 1024, 768));
        Stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
