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

    //do we need a date?

    //still to create constructors and getters-setters



}
