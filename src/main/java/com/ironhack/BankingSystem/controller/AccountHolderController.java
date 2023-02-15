package com.ironhack.BankingSystem.controller;

import com.ironhack.BankingSystem.dto.AccountHolderDTO;
import com.ironhack.BankingSystem.dto.TransactionDTO;
import com.ironhack.BankingSystem.model.transaction.Transaction;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.repository.AccountHolderRepository;
import com.ironhack.BankingSystem.service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;


@RestController
public class AccountHolderController {

    @Autowired
    AccountHolderService accountHolderService;



    @PostMapping("/create_account_holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createNewAccountHolder (@RequestBody AccountHolderDTO accountHolderDTO){
        return accountHolderService.createAccountHolder(accountHolderDTO);

    }

    @PostMapping ("/get_balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal accountHolderCheckBalance (@RequestParam Long accountId){
        return accountHolderService.checkBalance(accountId);
    }

    @PostMapping("/create_transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction makeTransaction (@RequestBody TransactionDTO transactionDTO){
        return accountHolderService.createTransaction(transactionDTO);
    }
}
