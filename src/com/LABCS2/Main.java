package com.LABCS2;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1800, 1000));
        primaryStage.show();
        primStage = primaryStage;
    }

    public static Stage getStage(){
        return primStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
