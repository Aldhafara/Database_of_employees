package com.sda.javafx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.javafx.controller.PersonController;
import com.sda.javafx.controller.PersonDetails;
import com.sda.javafx.model.Person;
import com.sda.javafx.model.PersonFX;
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

    private List<Person> personList = new ArrayList<>();
    private ObservableList<PersonFX> personFXList = FXCollections.observableArrayList();


    public  Main() throws IOException{

/*        personList.add(new Person("Jan", "Kowalski"));
        personList.add(new Person("Piotr","Kowalski"));
        personList.add(new Person("Krzysztof","Kowalski"));
        personList.add(new Person("Jurgen","Kowalski"));
        personList.add(new Person("Jan","Nowak"));
        personList.add(new Person("Jan","Wiśniewski"));
        personList.add(new Person("Grzegorz","Kowalczyk"));
        personList.add(new Person("Jan", "Kowalski"));
        personList.add(new Person("Stefan", "Kosiński"));
        personList.add(new Person("Jacek", "Kawalec"));
        personList.add(new Person("Jan", "Kowalski"));
        personList.add(new Person("Gaweł", "Paweł"));
        personList.add(new Person("Jan", "Kowalski"));
        personList.add(new Person("Jakub", "Kowalski"));
        personList.add(new Person("Grażyna", "Ruda"));*/



        ObjectMapper mapper = new ObjectMapper();
/*        File filename = new File("src\\main\\resources\\person.json");
        filename.createNewFile();
        mapper.writeValue(filename, personList);*/

        Person[] readorders = mapper.readValue(new File("src\\main\\resources\\person.json"), Person[].class);
        for(Person p:  readorders){
            System.out.println(p.getName());
            personFXList.add(new PersonFX(p.getName(), p.getLastName(),p.getStreet(), p.getCity(), p.getPostalCode(),p.getTelephone()));
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

    public void loadPersonEdit(PersonFX personFX){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/editPerson.fxml"));

            VBox window = (VBox) loader.load();

            PersonDetails personDetails = loader.getController();
            personDetails.setPersonFX(personFX);

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

    public void loadPersonNew(PersonFX personFX){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/newPerson.fxml"));

            VBox window = (VBox) loader.load();

            PersonDetails personDetails = loader.getController();
            personDetails.setMain(this);

            Stage editStage = new Stage();
            personDetails.setStage(editStage);
            personDetails.setPersonFX(personFX);
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

    public List<Person> getPersonList() {
        return personList;
    }

    public ObservableList<PersonFX> getPersonFXList() {
        return personFXList;
    }
}
