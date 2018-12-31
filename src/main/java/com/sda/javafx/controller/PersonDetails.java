package com.sda.javafx.controller;

import com.sda.javafx.Main;
import com.sda.javafx.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonDetails {

    @FXML
    private TextField name;
    @FXML
    private TextField lastName;
    @FXML
    private TextField street;
    @FXML
    private TextField city;
    @FXML
    private TextField postalCode;
    @FXML
    private TextField telephonNumber;
    private Person person;
    private Stage stage;

    @FXML
    public void initialize(){
        lastName.setText("To jest test");

    }
    public void setPerson(Person person){

        this.person = person;
        if(person != null) {
            name.setText(person.getName());
            lastName.setText(person.getLastName());
        }
    }
    @FXML
    public void handleOk() {
        person.setName(name.getText());
        person.setLastName(lastName.getText());
        System.out.println("Zapisz");
        this.stage.close();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    public void handleCancel() {
        this.stage.close();
    }
}
