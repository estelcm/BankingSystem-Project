package com.ironhack.BankingSystem.dto;

import java.math.BigDecimal;

public class AccountDTO {
    private BigDecimal newBalance;

    private String newSecretKey;

    private Integer newPrimaryOwnerId;

    private Integer newSecondaryOwnerId;

    //interestRate needed for SavingsAccount && CreditCard
    private Double newInterestRate;

  //creditLimit just needed for CreditCard
  private BigDecimal newCreditLimit;


    public AccountDTO(BigDecimal newBalance, String newSecretKey, Integer newPrimaryOwner, Integer newSecondaryOwner, Double newInterestRate, BigDecimal newCreditLimit) {
        this.newBalance = newBalance;
        this.newSecretKey = newSecretKey;
        this.newPrimaryOwnerId = newPrimaryOwner;
        this.newSecondaryOwnerId = newSecondaryOwner;
        this.newInterestRate = newInterestRate;
        this.newCreditLimit = newCreditLimit;
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

    public Integer getNewPrimaryOwnerId() {
        return newPrimaryOwnerId;
    }

    public void setNewPrimaryOwnerId(Integer newPrimaryOwnerId) {
        this.newPrimaryOwnerId = newPrimaryOwnerId;
    }

    public Integer getNewSecondaryOwnerId() {
        return newSecondaryOwnerId;
    }

    public void setNewSecondaryOwnerId(Integer newSecondaryOwnerId) {
        this.newSecondaryOwnerId = newSecondaryOwnerId;
    }



    public Double getNewInterestRate() {
        return newInterestRate;
    }

    public void setNewInterestRate(Double interestRate) {
        this.newInterestRate = interestRate;
    }

    public BigDecimal getNewCreditLimit() {
        return newCreditLimit;
    }

    public void setNewCreditLimit(BigDecimal creditLimit) {
        this.newCreditLimit = creditLimit;
    }
}


