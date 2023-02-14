package com.ironhack.BankingSystem.service;


import com.ironhack.BankingSystem.controller.AccountController;
import com.ironhack.BankingSystem.controller.AdminController;
import com.ironhack.BankingSystem.dto.AccountDTO;
import com.ironhack.BankingSystem.model.Accounts.*;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.repository.AccountHolderRepository;
import com.ironhack.BankingSystem.repository.AccountRepository;
import com.ironhack.BankingSystem.repository.AdminRepository;
import com.ironhack.BankingSystem.repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
public class AdminService {

    @Autowired
    AdminController adminController;

    @Autowired
    AccountController accountController;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    SavingsRepository savingsRepository;


    /*
    When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account should be created otherwise a regular Checking Account should be created.
     */
    public Account createCheckingAccount(AccountDTO accountDTO) {


        if (accountHolderRepository.findById(accountDTO.getNewPrimaryOwnerId()).isPresent()) {
            AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getNewPrimaryOwnerId()).get();
            AccountHolder secondaryOwner = null;

            if (accountDTO.getNewSecondaryOwnerId() != null) {
                if (accountHolderRepository.findById(accountDTO.getNewSecondaryOwnerId()).isPresent()) {
                    secondaryOwner = accountHolderRepository.findById(accountDTO.getNewSecondaryOwnerId()).get();
                }
            }


            if (Period.between(primaryOwner.getDateOfBirth(), LocalDate.now()).getYears() >= 24) {
                return accountRepository.save(new Checking(accountDTO.getNewBalance(), accountDTO.getNewSecretKey(), primaryOwner, secondaryOwner));

            } else {
                return accountRepository.save(new StudentChecking(accountDTO.getNewBalance(), accountDTO.getNewSecretKey(), primaryOwner, secondaryOwner));
            }
        }

        throw new IllegalArgumentException("Before creating the Account,a primary Account Holder needs to be created");
    }

    public Account createSavingsAccount(AccountDTO accountDTO) throws Exception {

        if (accountHolderRepository.findById(accountDTO.getNewPrimaryOwnerId()).isPresent()) {
            AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getNewPrimaryOwnerId()).get();
            AccountHolder secondaryOwner = null;
            if (accountDTO.getNewSecondaryOwnerId() != null) {
                if (accountHolderRepository.findById(accountDTO.getNewSecondaryOwnerId()).isPresent()) {
                    secondaryOwner = accountHolderRepository.findById(accountDTO.getNewSecondaryOwnerId()).get();
                }
            }
            return accountRepository.save(new Savings(accountDTO.getNewBalance(), accountDTO.getNewSecretKey(), primaryOwner, secondaryOwner, accountDTO.getNewInterestRate(), accountDTO.getNewCreditLimit()));
        }

        throw new IllegalArgumentException("Before creating a Savings Account,a primary Account Holder needs to be created");

    }

    public Account createCreditCardAccount(AccountDTO accountDTO) throws Exception {
        if (accountHolderRepository.findById(accountDTO.getNewPrimaryOwnerId()).isPresent()) {
            AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getNewPrimaryOwnerId()).get();
            AccountHolder secondaryOwner = null;
            if (accountDTO.getNewSecondaryOwnerId() != null) {
                if (accountHolderRepository.findById(accountDTO.getNewSecondaryOwnerId()).isPresent()) {
                    secondaryOwner = accountHolderRepository.findById(accountDTO.getNewSecondaryOwnerId()).get();
                }
            }
            return accountRepository.save(new CreditCard (accountDTO.getNewBalance(),accountDTO.getNewSecretKey(),primaryOwner, secondaryOwner,accountDTO.getNewCreditLimit(),accountDTO.getNewInterestRate()));
        }
        throw new IllegalArgumentException("Before creating a CreditCard Account,a primary Account Holder needs to be created");
    }

    /* Check balance*/

    public BigDecimal checkBalance (Long accountId){
       Account account = accountRepository.findById(accountId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

       return account.getBalance();

    }

    public Account updateAccount (AccountDTO accountDTO, Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));


        if (accountDTO.getNewBalance() != null) account.setBalance(accountDTO.getNewBalance());
        if (accountDTO.getNewSecretKey() != null) account.setSecretKey(accountDTO.getNewSecretKey());
        if (accountDTO.getNewPrimaryOwnerId() != null && accountHolderRepository.findById(accountDTO.getNewPrimaryOwnerId()).isPresent()) {
            AccountHolder newPrimaryOwnerId = accountHolderRepository.findById(accountDTO.getNewPrimaryOwnerId()).get();
            account.setPrimaryOwner(newPrimaryOwnerId);
        }
        if ( accountDTO.getNewSecondaryOwnerId() != null && accountHolderRepository.findById(accountDTO.getNewSecondaryOwnerId()).isPresent()) {
            AccountHolder newSecondaryOwnerId = accountHolderRepository.findById(accountDTO.getNewSecondaryOwnerId()).get();
            account.setSecondaryOwner(newSecondaryOwnerId);
        }


        if (account instanceof Savings) {
            //mirar si tot el q tenen en comu ens ho han passat
//instanceOf or isInstance()?¿?¿?¿

            //check per each account id that id is from (savings, creditcard...)
            //
            return null;
        }
        return accountRepository.save(account);
    }

}
