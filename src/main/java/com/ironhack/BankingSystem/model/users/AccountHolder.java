package com.ironhack.BankingSystem.model.users;

import com.ironhack.BankingSystem.model.Accounts.Account;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
public class AccountHolder extends User{

    private LocalDate dateOfBirth;

    @NotNull
    @Embedded
    private Address primaryAddress;
    @Embedded
    private Address mailingAddress;

   @OneToMany(mappedBy = "primaryOwner")
   private List<Account> primaryOwnerAccounts= new ArrayList<>();


   @OneToMany(mappedBy = "secondaryOwner")
   private List<Account> secondaryOwnerAccounts= new ArrayList<>();
}
