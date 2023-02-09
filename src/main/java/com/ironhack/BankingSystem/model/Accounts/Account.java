package com.ironhack.BankingSystem.model.Accounts;

import com.ironhack.BankingSystem.model.users.AccountHolders;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private BigDecimal Balance;

    private Integer secretKey;

    @NotNull
    @ManyToOne
    AccountHolders primaryOwner;
    @ManyToOne
    AccountHolders secondaryOwner;

    private BigDecimal penaltyFee;
    private LocalDate creationDate;

    private Status status;



}
