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
    private Integer accountId;

    private BigDecimal balance;

    private Integer secretKey;

    @NotNull
    @ManyToOne
    //@JoinColumn(name="") will be needed?
    AccountHolder primaryOwner;
    @ManyToOne
    AccountHolder secondaryOwner;

    private BigDecimal penaltyFee;




}
