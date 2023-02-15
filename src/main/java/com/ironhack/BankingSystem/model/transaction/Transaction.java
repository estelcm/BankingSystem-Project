package com.ironhack.BankingSystem.model.transaction;


import com.ironhack.BankingSystem.model.Accounts.Account;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private String targetAccountHolderName;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name="origin_account_id")
    private Account originAccount;

    @ManyToOne
    @JoinColumn(name="target_account_id")
    private Account targetAccount;


    public Transaction() {
    }

    public Transaction(String targetAccountHolderName, BigDecimal amount, Account originAccount, Account targetAccount) {
        this.targetAccountHolderName = targetAccountHolderName;
        this.amount = amount;
        this.originAccount = originAccount;
        this.targetAccount = targetAccount;

    }


    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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

    public Account getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(Account originAccount) {
        this.originAccount = originAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }
}
