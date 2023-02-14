package com.ironhack.BankingSystem.repository;

import com.ironhack.BankingSystem.model.Accounts.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRespository extends JpaRepository <CreditCard, Long> {
}
