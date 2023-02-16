package com.ironhack.BankingSystem.repository;

import com.ironhack.BankingSystem.dto.AccountDTO;
import com.ironhack.BankingSystem.model.Accounts.Account;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    //Optional<Account> findByIdAndPrimaryOwnerName(Long accountId, String accountHolder);
   // Optional<Account> findByIdAndSecondaryOwnerId(Long accountId, Long accountHolderId);

    Optional <Account> findBySecretKey (String secretKey);

}
