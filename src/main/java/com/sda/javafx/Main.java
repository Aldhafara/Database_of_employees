package com.sda.javafx;

import com.sda.javafx.controller.PersonController;
import com.sda.javafx.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;

public class Main extends Application {

    private Stage stage;
    private VBox layout;

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    public  Main(){
        personList.add(new Person("Jan","Kowalski"));
        personList.add(new Person("Piotr","Kowalski"));
        personList.add(new Person("Krzysztof","Kowalski"));
        personList.add(new Person("Jurgen","Kowalski"));
        personList.add(new Person("Jan","Nowak"));
        personList.add(new Person("Jan","Wiśniewski"));
        personList.add(new Person("Grzegorz","Kowalczyk"));


    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }

    public static void main(String[] args) {

        launch();




    }

    public void start (Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        this.stage.setTitle("Moja aplikacja w JavaFX");
        loadView();
    }

    public void loadView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/rootView.fxml"));

            layout = (VBox) loader.load();

            Scene scene = new Scene(layout);
            stage.setScene(scene);
            stage.show();

            PersonController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
        e.printStackTrace();
    }

    }

    public void loadPersonEdit(){

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/personEdit.fxml"));

            VBox window = (VBox) loader.load();

            Stage editStage = new Stage();
            editStage.setTitle("Edytuj osobę");
            Scene scene = new Scene(window);
            editStage.setScene(scene);
            editStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
