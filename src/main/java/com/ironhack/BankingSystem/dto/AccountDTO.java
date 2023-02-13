package com.ironhack.BankingSystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountDTO {
    private BigDecimal newBalance;

    private String newSecretKey;

    private Integer newPrimaryOwner;

    private Integer newSecundaryOwner;

    private final BigDecimal penaltyFee= new BigDecimal("40");

    private final LocalDate creationDate = LocalDate.now();

    //primary owner y secundary owner Long


    public AccountDTO(BigDecimal newBalance, String newSecretKey, Integer newPrimaryOwner, Integer newSecundaryOwner) {
        this.newBalance = newBalance;
        this.newSecretKey = newSecretKey;
        this.newPrimaryOwner = newPrimaryOwner;
        this.newSecundaryOwner = newSecundaryOwner;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

    public String getNewSecretKey() {
        return newSecretKey;
    }

    public void setNewSecretKey(String newSecretKey) {
        this.newSecretKey = newSecretKey;
    }

    public Integer getNewPrimaryOwner() {
        return newPrimaryOwner;
    }

    public void setNewPrimaryOwner(Integer newPrimaryOwner) {
        this.newPrimaryOwner = newPrimaryOwner;
    }

    public Integer getNewSecundaryOwner() {
        return newSecundaryOwner;
    }

    public void setNewSecundaryOwner(Integer newSecundaryOwner) {
        this.newSecundaryOwner = newSecundaryOwner;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}


