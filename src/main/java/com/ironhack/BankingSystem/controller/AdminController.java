package com.ironhack.BankingSystem.controller;

import com.ironhack.BankingSystem.dto.AccountDTO;
import com.ironhack.BankingSystem.dto.AccountHolderDTO;
import com.ironhack.BankingSystem.dto.ThirdPartyDTO;
import com.ironhack.BankingSystem.dto.TransactionDTO;
import com.ironhack.BankingSystem.model.Accounts.Account;
import com.ironhack.BankingSystem.model.Accounts.Savings;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.model.users.ThirdParty;
import com.ironhack.BankingSystem.model.users.User;
import com.ironhack.BankingSystem.repository.AccountRepository;
import com.ironhack.BankingSystem.service.AdminService;
import com.ironhack.BankingSystem.service.ThirdPartyService;
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

    @Autowired
    ThirdPartyService thirdPartyService;

    @PostMapping("/create_account_holder")
    @ResponseStatus(HttpStatus.CREATED)
    public User createNewAccountHolder (@RequestBody AccountHolderDTO accountHolderDTO){
        return adminService.createAccountHolder(accountHolderDTO);

    }
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
    public Account updateAccountData(@RequestBody AccountDTO accountDTO, @PathVariable Long accountId) throws Exception {
        return adminService.updateAccount(accountDTO, accountId);
    }

    @DeleteMapping("/delete_account/{accountId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAccountAndData (@PathVariable Long accountId){
        adminService.deleteAccount(accountId);
    }

    @PostMapping("/create_third_party")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdParty(@RequestBody ThirdPartyDTO thirdPartyDTO) {
        return adminService.createThirdPartyUser(thirdPartyDTO);
    }


}
