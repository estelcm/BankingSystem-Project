package com.ironhack.BankingSystem.model.Accounts;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
class CreditCard extends Account{
    private BigDecimal creditLimit;

    private double interestRate;

}
