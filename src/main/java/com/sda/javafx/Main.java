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
/*        File filename = new File("persons.json");
        filename.createNewFile();
        mapper.writeValue(filename, personList);*/

        Person[] readorders = mapper.readValue(new File("persons.json"), Person[].class);
        for(Person p:  readorders){
            System.out.println(p.getName() +" " +p.getLastName());
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

    private void loadView(){
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/newPerson.fxml"));
        VBox window = null;
        try {
            window = (VBox) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

            PersonDetails personDetails = loader.getController();
            personDetails.setMain(this);
            Stage newPersonStage = new Stage();
            personDetails.setStage(newPersonStage);
            personDetails.setPersonFX(personFX);
            newPersonStage.setTitle("Dodaj osobę");
        Scene scene = null;
        if (window != null) {
            scene = new Scene(window);
        }
        newPersonStage.setScene(scene);
            newPersonStage.show();

    }

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

    public void removeFromPersonFXList(PersonFX person){
        personFXList.remove(person);
    }

    public void wypiszListęFX(){
        for (PersonFX p : personFXList) {
            System.out.println(p.getName() +" " +p.getLastName());
        }
        int i = personFXList.size();
        System.out.print("Lista ma " + i);
        if (i==1)
            System.out.println(" rekord");
        else if (i>1 && i<5)
            System.out.println(" rekordy");
        else System.out.println(" rekordów");
    }
}
