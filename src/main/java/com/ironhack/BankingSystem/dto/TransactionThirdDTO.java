package com.ironhack.BankingSystem.dto;

import java.math.BigDecimal;

public class TransactionThirdDTO {

    private String hashedKey;

    private Long involvedAccountId;

    private BigDecimal amount;


    private String secretKey;


    public TransactionThirdDTO(String hashedKey, Long involvedAccountId, BigDecimal amount, String secretKey) {
        this.hashedKey = hashedKey;
        this.involvedAccountId = involvedAccountId;
        this.amount = amount;
        this.secretKey = secretKey;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    public Long getInvolvedAccountId() {
        return involvedAccountId;
    }

    public void setInvolvedAccountId(Long involvedAccountId) {
        this.involvedAccountId = involvedAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
