package com.sda.javafx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

    private StringProperty name;
    private StringProperty lastName;
    private StringProperty street;
    private StringProperty city;
    private StringProperty postalCode;
    private StringProperty telephone;

    public Person(){}

    public Person(String name, String lastName){
        this.name = new SimpleStringProperty(name);
        this.lastName = new SimpleStringProperty(lastName);
    }

}
