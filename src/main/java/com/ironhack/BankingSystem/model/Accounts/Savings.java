package com.ironhack.BankingSystem.model.Accounts;

import com.ironhack.BankingSystem.model.Accounts.Enums.Status;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Savings extends Account {
    private Double interestRate=0.0025;


    private BigDecimal minimumBalance= new BigDecimal("1000");

    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;

    public Savings() {
    }

    public Savings(BigDecimal balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, Double interestRate,  BigDecimal minimumBalance) throws Exception {
        super(balance, secretKey, primaryOwner, secondaryOwner);
        setInterestRate(interestRate);
        setMinimumBalance(minimumBalance);
    }

    public Double getInterestRate() {
        return interestRate;
    }


    public void setInterestRate(Double interestRate) throws Exception {
            if(interestRate == null){
                this.interestRate= 0.0025;
            }
            if(interestRate < 0.0025 || interestRate> 0.5) {
                throw new IllegalArgumentException("Interest rate should be between 0.0025 and 0.5");
            } else {
                this.interestRate = interestRate;
            }
            /*    BigDecimal minRate = new BigDecimal("0.0025");
    BigDecimal maxRate = new BigDecimal("0.5");

    if (interestRate.compareTo(minRate) < 0 || interestRate.compareTo(maxRate) > 0) {
        throw new Exception("Interest rate should be between 0.0025 and 0.5");
    }*/
    }



    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }


    public void setMinimumBalance(BigDecimal minimumBalance) throws Exception {
        if(minimumBalance == null){
            this.minimumBalance= new BigDecimal("1000");
        }
        if(minimumBalance.compareTo(new BigDecimal("1000")) == -1 || minimumBalance.compareTo(new BigDecimal("100")) == 1){
            throw new IllegalArgumentException("Minimum balance should be between 1000 and 100");
        }
        this.minimumBalance = minimumBalance;
    }
}
