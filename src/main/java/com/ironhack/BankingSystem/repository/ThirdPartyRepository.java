package com.ironhack.BankingSystem.repository;


import com.ironhack.BankingSystem.model.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

@Repository
public interface ThirdPartyRepository extends JpaRepository <ThirdParty, Integer> {
}
