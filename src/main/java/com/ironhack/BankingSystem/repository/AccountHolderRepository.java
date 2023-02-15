package com.ironhack.BankingSystem.repository;

import com.ironhack.BankingSystem.model.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer> {

    Optional<AccountHolder> findByName(String name);
}
