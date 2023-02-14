package com.ironhack.BankingSystem.repository;

import com.ironhack.BankingSystem.model.Accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, Long> {
}
