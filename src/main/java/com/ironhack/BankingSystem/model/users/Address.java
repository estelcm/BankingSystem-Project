package com.ironhack.BankingSystem.model.users;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String number;
    private String city;
    private String postCode;
    private String country;
}
