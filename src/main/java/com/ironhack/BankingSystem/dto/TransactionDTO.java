package com.ironhack.BankingSystem.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ironhack.BankingSystem.utils.LocalDateDeserializer;
import com.ironhack.BankingSystem.utils.LocalDateSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {

    private String targetAccountHolderName;

    private BigDecimal amount;

    private Long originAccountId;

    private Long targetAccountId;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime transactionTime= LocalDateTime.now();

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

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
}
