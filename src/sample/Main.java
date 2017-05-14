package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.*;
import javafx.stage.Stage;
import java.lang.reflect.*;

import java.sql.Statement;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            loader.setController(new Controller());
            Parent root = loader.load();
            primaryStage.setTitle("IndProject");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    public static void main(String[] args) {

        launch(args);
    }
}
