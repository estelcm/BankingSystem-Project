package com.ironhack.BankingSystem.dto;

import com.ironhack.BankingSystem.model.users.Address;

import java.time.LocalDate;
import java.util.Optional;

public class AccountHolderDTO {
    private String newName;

    private LocalDate newDateOfBirth;

    private Address newPrimaryAddress;

    private Address newMailingAddress;



    public AccountHolderDTO(String newName, LocalDate newDateOfBirth, Address newPrimaryAddress, Address newMailingAddress, Long newPrimaryOwnerId, Long newSecondaryOwnerId) {
        this.newName = newName;
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


}
