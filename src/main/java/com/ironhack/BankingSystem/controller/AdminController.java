package com.ironhack.BankingSystem.controller;

import com.ironhack.BankingSystem.dto.AccountDTO;
import com.ironhack.BankingSystem.model.Accounts.Account;
import com.ironhack.BankingSystem.model.Accounts.Savings;
import com.ironhack.BankingSystem.repository.AccountRepository;
import com.ironhack.BankingSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;


    @Autowired
    AccountRepository accountRepository;


    @PostMapping("/create_checking_account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewCheckingAccount(@RequestBody AccountDTO accountDTO) {
        return adminService.createCheckingAccount(accountDTO);
    }

    @PostMapping("/create_savings_account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewSavingsAccount(@RequestBody AccountDTO accountDTO) throws Exception {
        return adminService.createSavingsAccount(accountDTO);
    }

    @PostMapping("/create_credit_card_account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewCreditCardAccount(@RequestBody AccountDTO accountDTO) throws Exception {
        return adminService.createCreditCardAccount(accountDTO);
    }

    @GetMapping("/check_balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getTheBalance (@RequestParam Long accountId){
        return adminService.checkBalance(accountId);
    }

    @PatchMapping("/update_account/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccountData(@RequestBody AccountDTO accountDTO, @PathVariable Long accountId){
        return adminService.updateAccount(accountDTO, accountId);
    }


}
