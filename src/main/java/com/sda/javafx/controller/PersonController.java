package com.sda.javafx.controller;

import com.sda.javafx.Main;
import com.sda.javafx.model.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class PersonController {

    private Main main;

    @FXML
    private TableView<Person> personTable;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label telephoneLabel;
    @FXML
    private TableColumn<Person, String> firstnameColumn;
    @FXML
    private TableColumn<Person, String> lastnameColumn;



    @FXML
    public void initialize(){
        firstnameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        lastnameColumn.setCellValueFactory(cell -> cell.getValue().lastNameProperty());
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));

    }

    @FXML
    public void handleNewButton(){
        this.main.loadPersonNew();

    }

    @FXML
    public void handleEditButton() {
        Person selectPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectPerson != null) {
            System.out.println(selectPerson.getName());
            this.main.loadPersonEdit(selectPerson);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getStage());
            alert.setTitle("Brak osoby");
            alert.setContentText("Nikt nie został wybrany");
            alert.showAndWait();

        }

    }

    @FXML
    public void handleDeleteButton(){

        int index = personTable.getSelectionModel().getSelectedIndex();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Usunąć " + personTable.getSelectionModel().getSelectedItem().getName() + " " + personTable.getSelectionModel().getSelectedItem().getLastName() + " ?",ButtonType.YES,ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES){
            if(index>=0)
                personTable.getItems().remove(index);
        }
    }


    private void showPersonDetails(Person person){
        if(person != null) {
            firstNameLabel.setText(person.getName());
            lastNameLabel.setText(person.getLastName());
        }else{
            firstNameLabel.setText("");
            lastNameLabel.setText("");
        }
    }

    public void setMain(Main main) {
        this.main = main;
        personTable.setItems(this.main.getPersonList());
    }

    @FXML
    public void saveData() {

    }
}
