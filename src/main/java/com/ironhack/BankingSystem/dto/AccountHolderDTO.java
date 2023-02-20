package com.ironhack.BankingSystem.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ironhack.BankingSystem.model.users.Address;
import com.ironhack.BankingSystem.utils.LocalDateDeserializer;
import com.ironhack.BankingSystem.utils.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Optional;

public class AccountHolderDTO {
    private String newName;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate newDateOfBirth;

    private Address newPrimaryAddress;

    private Address newMailingAddress;

    private String newUserName;

    private String newPassword;


    public AccountHolderDTO(String newName, String newUserName,String newPassword,LocalDate newDateOfBirth, Address newPrimaryAddress, Address newMailingAddress) {
        this.newName = newName;
        this.newUserName= newUserName;
        this.newPassword = newPassword;
        this.newDateOfBirth = newDateOfBirth;
        this.newPrimaryAddress = newPrimaryAddress;
        this.newMailingAddress = newMailingAddress;


    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public LocalDate getNewDateOfBirth() {
        return newDateOfBirth;
    }

    public void setNewDateOfBirth(LocalDate newDateOfBirth) {
        this.newDateOfBirth = newDateOfBirth;
    }

    public Address getNewPrimaryAddress() {
        return newPrimaryAddress;
    }

    public void setNewPrimaryAddress(Address newPrimaryAddress) {
        this.newPrimaryAddress = newPrimaryAddress;
    }

    public Address getNewMailingAddress() {
        return newMailingAddress;
    }

    public void setNewMailingAddress(Address newMailingAddress) {
        this.newMailingAddress = newMailingAddress;
    }

    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
