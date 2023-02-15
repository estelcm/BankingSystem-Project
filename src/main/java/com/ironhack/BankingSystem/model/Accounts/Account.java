package com.ironhack.BankingSystem.model.Accounts;

import com.ironhack.BankingSystem.model.users.AccountHolder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private BigDecimal balance;

    private String secretKey;



    @NotNull
    @ManyToOne
    @JoinColumn(name="primary_owner")
    AccountHolder primaryOwner;
    @ManyToOne
    @JoinColumn(name="secondary_owner")
    AccountHolder secondaryOwner;

    private final BigDecimal penaltyFee= new BigDecimal("40");

    private final LocalDate creationDate = LocalDate.now();

    private LocalDate LastPenaltyFee = LocalDate.now();

    public Account() {
    }

    public Account(BigDecimal balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;

    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getLastPenaltyFee() {
        return LastPenaltyFee;
    }

    public void setLastPenaltyFee(LocalDate lastPenaltyFee) {
        LastPenaltyFee = lastPenaltyFee;
    }
}
