package com.sda.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;
    private BorderPane layout;

    public static void main(String[] args) {


    }

    public void start (Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        this.stage.setTitle("Moja aplikacja w JavaFX");
        loadView();
    }

    public void loadView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("rootView.fxml"));

            layout = (BorderPane) loader.load();

            Scene scene = new Scene(layout);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
