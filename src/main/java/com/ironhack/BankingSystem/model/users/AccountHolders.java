package com.ironhack.BankingSystem.model.users;

import com.ironhack.BankingSystem.model.Accounts.Account;
import jakarta.persistence.Embedded;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

public class AccountHolders extends User{

    private LocalDate dateOfBirth;

    @Embedded
    private Address primaryAddress;
    @Embedded
    private Address mailingAddress;

   @OneToMany
   private List<Account> primaryOwner;


   @OneToMany
   private List<Account> secondaryOwner;
}
