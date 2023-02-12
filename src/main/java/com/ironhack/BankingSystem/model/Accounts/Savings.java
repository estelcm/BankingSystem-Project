package com.ironhack.BankingSystem.model.Accounts;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

@Entity
public class Savings extends Account {
    private double interestRate;

    private LocalDate creationDate;


    @Enumerated(EnumType.STRING)
    private Status status;

}
