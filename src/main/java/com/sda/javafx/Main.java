package com.sda.javafx;

//import com.sda.javafx.controller.Delete;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.javafx.controller.PersonController;
import com.sda.javafx.controller.PersonDetails;
import com.sda.javafx.model.Person;
import com.sda.javafx.model.PersonJSON;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Stage stage;
    private VBox layout;

    private ObservableList<Person> personList = FXCollections.observableArrayList();
    private List<PersonJSON> personJSONList = new ArrayList<>();


    public  Main() throws IOException{

        personJSONList.add(new PersonJSON("Jan", "Kowalski"));
        personJSONList.add(new PersonJSON("Piotr","Kowalski"));
        personJSONList.add(new PersonJSON("Krzysztof","Kowalski"));
        personJSONList.add(new PersonJSON("Jurgen","Kowalski"));
        personJSONList.add(new PersonJSON("Jan","Nowak"));
        personJSONList.add(new PersonJSON("Jan","Wiśniewski"));
        personJSONList.add(new PersonJSON("Grzegorz","Kowalczyk"));
        personJSONList.add(new PersonJSON("Jan", "Kowalski"));
        personJSONList.add(new PersonJSON("Stefan", "Kosiński"));
        personJSONList.add(new PersonJSON("Jacek", "Kawalec"));
        personJSONList.add(new PersonJSON("Jan", "Kowalski"));
        personJSONList.add(new PersonJSON("Gaweł", "Paweł"));
        personJSONList.add(new PersonJSON("Jan", "Kowalski"));
        personJSONList.add(new PersonJSON("Jakub", "Kowalski"));
        personJSONList.add(new PersonJSON("Grażyna", "Ruda"));



        ObjectMapper mapper = new ObjectMapper();
        File filename = new File("src\\main\\resources\\person.json");
        filename.createNewFile();
        mapper.writeValue(filename, personJSONList);

        PersonJSON[] readorders = mapper.readValue(new File("src\\main\\resources\\person.json"), PersonJSON[].class);

        for(PersonJSON p:  readorders){
            System.out.println(p.getName());
            personList.add(new Person(p.getName(), p.getLastname()));
//


    }

    }

    public static void main(String[] args) throws IOException {

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

    public void loadPersonEdit(Person person){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/editPerson.fxml"));

            VBox window = (VBox) loader.load();

            PersonDetails personDetails = loader.getController();
            personDetails.setPerson(person);

            Stage editStage = new Stage();
            editStage.setTitle("Edytuj osobę");

            personDetails.setStage(editStage);

            Scene scene = new Scene(window);
            editStage.setScene(scene);
            editStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPersonNew(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/newPerson.fxml"));

            VBox window = (VBox) loader.load();



            Stage editStage = new Stage();
            editStage.setTitle("Dodaj osobę");
            Scene scene = new Scene(window);
            editStage.setScene(scene);
            editStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void loadPersonDelete(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/delete.fxml"));

            VBox window = (VBox) loader.load();

            Delete deletePerson = loader.getController();
            deletePerson.setPerson(person);

            Stage editStage = new Stage();
            editStage.setTitle("Usuń osobę");

            deletePerson.setStage(editStage);

            Scene scene = new Scene(window);
            editStage.setScene(scene);
            editStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    private void loader(String path, String title, Person person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/" + path + ".fxml"));

            VBox window = (VBox) loader.load();

            PersonDetails personDetails = loader.getController();
            personDetails.setPerson(person);

            Stage editStage = new Stage();
            editStage.setTitle(title);
            Scene scene = new Scene(window);
            editStage.setScene(scene);
            editStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getStage() {
        return stage;
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }
}
