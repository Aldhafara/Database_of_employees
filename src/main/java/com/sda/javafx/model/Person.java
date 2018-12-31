package com.sda.javafx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

    private String name;
    private String lastName;
    private String street;
    private String city;
    private String postalCode;
    private String telephone;

    public Person(){
        this(null,null,null,null,null,null);
    }

    public Person(String name, String lastName, String street, String city, String postalCode, String telephone) {
        this.name = name;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.telephone = telephone;
    }

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
        this.street = null;
        this.city = null;
        this.postalCode = null;
        this.telephone = null;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;
    }

    public void setStreet(String street) {
        this.street=street;
    }

    public void setCity(String city) {
        this.city=city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode=postalCode;
    }

    public void setTelephone(String telephone) {
        this.telephone=telephone;
    }
}