package com.ironhack.BankingSystem.model.Accounts;

import com.ironhack.BankingSystem.model.Accounts.Enums.Status;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Checking extends Account{
    private final BigDecimal minimumBalance = new BigDecimal("250");
    private final BigDecimal monthlyMaintenanceFee = new BigDecimal("12");



    @Enumerated(EnumType.STRING)
    private Status status= Status.ACTIVE;

    public Checking() {
    }

    public Checking(BigDecimal balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, secretKey, primaryOwner, secondaryOwner);


    }


    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }



    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }





    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
