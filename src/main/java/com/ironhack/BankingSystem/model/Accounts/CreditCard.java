package com.ironhack.BankingSystem.model.Accounts;

import com.ironhack.BankingSystem.model.users.AccountHolder;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class CreditCard extends Account{
    private BigDecimal creditLimit= new BigDecimal("100");

    private Double interestRate = 0.2;

    private LocalDate lastMonthInterestRate = LocalDate.now();


    public CreditCard() {
    }

    public CreditCard(BigDecimal balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal creditLimit, Double interestRate) throws Exception {
        super(balance, secretKey, primaryOwner, secondaryOwner);
     setCreditLimit(creditLimit);
    setInterestRate(interestRate);
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }


    public void setCreditLimit(BigDecimal creditLimit) throws Exception {
        if(creditLimit== null ){
          this.creditLimit= new BigDecimal("100");
        }
        if (creditLimit.compareTo(new BigDecimal("100")) == 1 || creditLimit.compareTo(new BigDecimal("100000")) == -1 ) {
            throw new IllegalArgumentException(" Credit Limit should be higher than 100 but not higher than 100000");
        }
        else {
            this.creditLimit = creditLimit;
        }
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) throws Exception{
       if(interestRate == null){
           this.interestRate=0.2;
       }
        if(interestRate>0.2 || interestRate<0.1){
            throw new IllegalArgumentException("Interest Rate should be less than 0.2 and not lower than 0.1");
        } else {
            this.interestRate = interestRate;
        }
    }

    public LocalDate getLastMonthInterestRate() {
        return lastMonthInterestRate;
    }

    public void setLastMonthInterestRate(LocalDate lastMonthInterestRate) {
        this.lastMonthInterestRate = lastMonthInterestRate;
    }

    public void applyInterestRate () {
        if(Period.between(lastMonthInterestRate,LocalDate.now()).getMonths()>1){
            setLastMonthInterestRate(LocalDate.now());
            setBalance(getBalance().add(getBalance().multiply(new BigDecimal(interestRate))));
        }
    }
    //metodo applyinterestRate
    //comparar ultima data amb data actual i shi ha passat mes d'un any ho passes.
}
