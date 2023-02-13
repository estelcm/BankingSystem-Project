package com.ironhack.BankingSystem.model.Accounts;

import com.ironhack.BankingSystem.model.Accounts.Enums.Status;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class StudentChecking extends Account{


    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;


    public StudentChecking() {
    }

    public StudentChecking(BigDecimal balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, secretKey, primaryOwner, secondaryOwner);


    }




    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
