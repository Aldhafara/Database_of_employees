package com.sda.javafx.controller;

import com.sda.javafx.Main;
import com.sda.javafx.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


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
    private Button newButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;

    @FXML
    public void initialize(){
        firstnameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        lastnameColumn.setCellValueFactory(cell -> cell.getValue().lastNameProperty());
    }

    @FXML
    public void handleNewButton(){
        this.main.loadPersonEdit();

    }

    public void setMain(Main main) {
        this.main = main;
        personTable.setItems(main.getPersonList());
    }
}
