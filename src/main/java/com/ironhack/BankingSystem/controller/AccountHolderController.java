package com.ironhack.BankingSystem.controller;

import com.ironhack.BankingSystem.dto.AccountHolderDTO;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.repository.AccountHolderRepository;
import com.ironhack.BankingSystem.service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountHolderController {

    @Autowired
    AccountHolderService accountHolderService;



    @PostMapping("/create_account_holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createNewAccountHolder (@RequestBody AccountHolderDTO accountHolderDTO){
        return accountHolderService.createAccountHolder(accountHolderDTO);

    }
}
