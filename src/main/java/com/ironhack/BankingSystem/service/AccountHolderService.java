package com.ironhack.BankingSystem.service;


import com.ironhack.BankingSystem.dto.AccountHolderDTO;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.repository.AccountHolderRepository;
import com.ironhack.BankingSystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderService {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public AccountHolder createAccountHolder;



    public AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO) {
        AccountHolder accountHolder = new AccountHolder(accountHolderDTO.getNewName(), accountHolderDTO.getNewDateOfBirth(), accountHolderDTO.getNewPrimaryAddress(), accountHolderDTO.getNewMailingAddress());
        return accountHolderRepository.save(accountHolder);
    }

}
