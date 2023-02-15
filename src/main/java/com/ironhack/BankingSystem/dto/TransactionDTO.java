package com.ironhack.BankingSystem.dto;

import java.math.BigDecimal;

public class TransactionDTO {

    private String targetAccountHolderName;

    private BigDecimal amount;

    private Long originAccountId;

    private Long targetAccountId;

    public TransactionDTO(String targetAccountHolderName, BigDecimal amount, Long originAccountId, Long targetAccountId) {
        this.targetAccountHolderName = targetAccountHolderName;
        this.amount = amount;
        this.originAccountId = originAccountId;
        this.targetAccountId = targetAccountId;
    }

    public String getTargetAccountHolderName() {
        return targetAccountHolderName;
    }

    public void setTargetAccountHolderName(String targetAccountHolderName) {
        this.targetAccountHolderName = targetAccountHolderName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getOriginAccountId() {
        return originAccountId;
    }

    public void setOriginAccountId(Long originAccountId) {
        this.originAccountId = originAccountId;
    }

    public Long getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(Long targetAccountId) {
        this.targetAccountId = targetAccountId;
    }
}
