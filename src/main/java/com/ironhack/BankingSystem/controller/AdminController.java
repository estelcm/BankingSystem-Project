package com.ironhack.BankingSystem.controller;

import com.ironhack.BankingSystem.dto.AccountDTO;
import com.ironhack.BankingSystem.model.Accounts.Account;
import com.ironhack.BankingSystem.model.Accounts.Savings;
import com.ironhack.BankingSystem.repository.AccountRepository;
import com.ironhack.BankingSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;


    @Autowired
    AccountRepository accountRepository;


    @PostMapping("/create_checking_account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewCheckingAccount (@RequestBody AccountDTO accountDTO){
        return adminService.createCheckingAccount(accountDTO);
    }

    @PostMapping ("/create_savings_account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewSavingsAccount (@RequestBody AccountDTO accountDTO) throws Exception {
        return adminService.createSavingsAccount(accountDTO);
    }

    @PostMapping("/create_creditcard_account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewCreditCardAccount (@RequestBody AccountDTO accountDTO) throws Exception{
        return adminService.createCreditCardAccount(accountDTO);
    }
}
