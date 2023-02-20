package com.ironhack.BankingSystem.model.users;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ironhack.BankingSystem.model.Accounts.Account;
import com.ironhack.BankingSystem.utils.LocalDateDeserializer;
import com.ironhack.BankingSystem.utils.LocalDateSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
public class AccountHolder extends User{

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;

    //@NotNull
    @Embedded
    private Address primaryAddress;

    //create an override no deja crear tabla, no tener dos columnas con mismo nombre
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "mailing_street")),
            @AttributeOverride(name = "number", column = @Column(name = "mailing_number")),
            @AttributeOverride(name = "postCode", column = @Column(name = "mailing_post_code")),
            @AttributeOverride(name="city", column = @Column(name= "mailing_city")),
            @AttributeOverride(name="country", column = @Column(name= "mailing_country")),
    })

    private Address mailingAddress;


   @OneToMany(mappedBy = "primaryOwner")
   @JsonIgnore
   private List<Account> primaryOwnerAccounts= new ArrayList<>();


   @OneToMany(mappedBy = "secondaryOwner")
   @JsonIgnore
   private List<Account> secondaryOwnerAccounts= new ArrayList<>();


    public AccountHolder() {
    }

    public AccountHolder(String name, String username,String password, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress) {
        super(name,username, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public List<Account> getPrimaryOwnerAccounts() {
        return primaryOwnerAccounts;
    }

    public void setPrimaryOwnerAccounts(List<Account> primaryOwnerAccounts) {
        this.primaryOwnerAccounts = primaryOwnerAccounts;
    }

    public List<Account> getSecondaryOwnerAccounts() {
        return secondaryOwnerAccounts;
    }

    public void setSecondaryOwnerAccounts(List<Account> secondaryOwnerAccounts) {
        this.secondaryOwnerAccounts = secondaryOwnerAccounts;
    }
}
