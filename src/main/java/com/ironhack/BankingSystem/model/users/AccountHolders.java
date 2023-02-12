package com.ironhack.BankingSystem.model.users;

import com.ironhack.BankingSystem.model.Accounts.Account;
import jakarta.persistence.Embedded;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class AccountHolders extends User{

    private LocalDate dateOfBirth;

    @NotNull
    @Embedded
    private Address primaryAddress;
    @Embedded
    private Address mailingAddress;

   @OneToMany
   private List<Account> primaryOwner;


   @OneToMany
   private List<Account> secondaryOwner;
}
