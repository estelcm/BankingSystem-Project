package com.ironhack.BankingSystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountDTO {
    private BigDecimal newBalance;

    private String newSecretKey;

    private Integer newPrimaryOwner;

    private Integer newSecondaryOwner;



    //interestRate needed for SavingsAccount && CreditCard
    private BigDecimal newInterestRate;

  //creditLimit just needed for CreditCard
  private BigDecimal newCreditLimit;


    public AccountDTO(BigDecimal newBalance, String newSecretKey, Integer newPrimaryOwner, Integer newSecondaryOwner, BigDecimal interestRate, BigDecimal creditLimit) {
        this.newBalance = newBalance;
        this.newSecretKey = newSecretKey;
        this.newPrimaryOwner = newPrimaryOwner;
        this.newSecondaryOwner = newSecondaryOwner;
        this.newInterestRate = interestRate;
        this.newCreditLimit = creditLimit;
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

    public Integer getNewSecondaryOwner() {
        return newSecondaryOwner;
    }

    public void setNewSecondaryOwner(Integer newSecondaryOwner) {
        this.newSecondaryOwner = newSecondaryOwner;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public BigDecimal getNewInterestRate() {
        return newInterestRate;
    }

    public void setNewInterestRate(BigDecimal interestRate) {
        this.newInterestRate = interestRate;
    }

    public BigDecimal getNewCreditLimit() {
        return newCreditLimit;
    }

    public void setNewCreditLimit(BigDecimal creditLimit) {
        this.newCreditLimit = creditLimit;
    }
}


