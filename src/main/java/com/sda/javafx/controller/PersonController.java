package com.sda.javafx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.javafx.Main;
import com.sda.javafx.model.Person;
import com.sda.javafx.model.PersonFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class PersonController {

    private Main main;

    @FXML
    private TableView<PersonFX> personTable;
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
    private TableColumn<PersonFX, String> firstnameColumn;
    @FXML
    private TableColumn<PersonFX, String> lastnameColumn;



    @FXML
    public void initialize(){
        firstnameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());
        lastnameColumn.setCellValueFactory(cell -> cell.getValue().lastNameProperty());
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));

    }

    @FXML
    public void handleNewButton(){
        PersonFX personFX = new PersonFX();
        this.main.loadPersonNew(personFX);
        main.getPersonFXList().add(personFX);
    }

    @FXML
    public void handleEditButton() {
        PersonFX selectPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectPerson != null) {
            System.out.println("-------->      Edytuję " + selectPerson.getName() + " "+ selectPerson.getLastName() + "      <--------");
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
            {
                System.out.println("-------->      Usuwam " + personTable.getSelectionModel().getSelectedItem().getName()
                        + " " + personTable.getSelectionModel().getSelectedItem().getLastName() + "      <--------");
                main.removeFromPersonFXList(personTable.getItems().remove(index));
                main.wypiszListęFX();
            }
        }
    }


    private void showPersonDetails(PersonFX person){
        if(person != null) {
            firstNameLabel.setText(person.getName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            cityLabel.setText(person.getCity());
            postalCodeLabel.setText(person.getPostalCode());
            telephoneLabel.setText(person.getTelephone());
        }else{
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            cityLabel.setText("");
            postalCodeLabel.setText("");
            telephoneLabel.setText("");
        }
    }

    public void setMain(Main main) {
        this.main = main;
        personTable.setItems(this.main.getPersonFXList());
    }

    @FXML
    public void saveData() {

    }

    @FXML
    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON Files", "*.json"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(main.getStage());
        if (selectedFile != null) {
            System.out.println("Wybrano plik: "+ selectedFile.getAbsolutePath());
            loadData(selectedFile);
        }
    }

    @FXML
    public void saveFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<PersonFX> personFXlist = new ArrayList<>();

        for (PersonFX p : main.getPersonFXList()) {
            personFXlist.add(new PersonFX(p.getName(), p.getLastName(), p.getStreet(), p.getCity(), p.getPostalCode(), p.getTelephone()));
        }

        File filename = new File("persons.json");
        //mapper.writeValue(filename, personFXlist);
        mapper.writeValue(filename, main.getPersonFXList());
        System.out.println("<--------      Zapisuję      -------->");
        main.wypiszListęFX();
    }

    private void loadData(File file) {
        Path pathToFile = Paths.get(file.getAbsolutePath());

        ObjectMapper mapper = new ObjectMapper();
        Person[] readPerson = new Person[0];
        try {
            readPerson = mapper.readValue(new File(String.valueOf(pathToFile)), Person[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<PersonFX> personFXList = FXCollections.observableArrayList();
        for (Person p : readPerson) {
            System.out.println(p.getName());
            personFXList.add(new PersonFX(p.getName(), p.getLastName(), p.getStreet(), p.getCity(), p.getPostalCode(), p.getTelephone()));
        }

        // Ustawienie danych dla tabeli, wykorzystujemy utworzoną powyżej listę
        personTable.itemsProperty().setValue(personFXList);

        // Powiązanie pierwszej kolumny z polem name obiektu typu PieChart.Data
        firstnameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());

        // Powiązanie drugiej kolumny z polem pieValue PieChart.Data
        lastnameColumn.setCellValueFactory(cell -> cell.getValue().lastNameProperty());
    }
}
