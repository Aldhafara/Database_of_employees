package com.sda.javafx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonFX {

    private StringProperty name;
    private StringProperty lastName;
    private StringProperty street;
    private StringProperty city;
    private StringProperty postalCode;
    private StringProperty telephone;

    public PersonFX(){}

    public PersonFX(String name, String lastName){
        this.name = new SimpleStringProperty(name);
        this.lastName = new SimpleStringProperty(lastName);

    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public String getPostalCode() {
        return postalCode.get();
    }

    public StringProperty postalCodeProperty() {
        return postalCode;
    }

    public String getTelephone() {
        return telephone.get();
    }

    public StringProperty telephoneProperty() {
        return telephone;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }
}
