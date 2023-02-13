package com.ironhack.BankingSystem.model.users;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String number;
    private String city;
    private String postCode;
    private String country;


    public Address() {
    }

    public Address(String street, String number, String city, String postCode, String country) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
