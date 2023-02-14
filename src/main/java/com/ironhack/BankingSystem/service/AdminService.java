package com.ironhack.BankingSystem.service;


import com.ironhack.BankingSystem.controller.AccountController;
import com.ironhack.BankingSystem.controller.AdminController;
import com.ironhack.BankingSystem.dto.AccountDTO;
import com.ironhack.BankingSystem.model.Accounts.*;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.repository.AccountHolderRepository;
import com.ironhack.BankingSystem.repository.AccountRepository;
import com.ironhack.BankingSystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    /*
    When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account should be created otherwise a regular Checking Account should be created.
     */
    public Account createCheckingAccount(AccountDTO accountDTO) {


        if (accountHolderRepository.findById(accountDTO.getNewPrimaryOwner()).isPresent()) {
            AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getNewPrimaryOwner()).get();
            AccountHolder secondaryOwner = null;

            if (accountDTO.getNewSecondaryOwner() != null) {
                if (accountHolderRepository.findById(accountDTO.getNewSecondaryOwner()).isPresent()) {
                    secondaryOwner = accountHolderRepository.findById(accountDTO.getNewSecondaryOwner()).get();
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

        if (accountHolderRepository.findById(accountDTO.getNewPrimaryOwner()).isPresent()) {
            AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getNewPrimaryOwner()).get();
            AccountHolder secondaryOwner = null;
            if (accountDTO.getNewSecondaryOwner() != null) {
                if (accountHolderRepository.findById(accountDTO.getNewSecondaryOwner()).isPresent()) {
                    secondaryOwner = accountHolderRepository.findById(accountDTO.getNewSecondaryOwner()).get();
                }
            }
            return accountRepository.save(new Savings(accountDTO.getNewBalance(), accountDTO.getNewSecretKey(), primaryOwner, secondaryOwner, accountDTO.getNewCreditLimit(), accountDTO.getNewInterestRate()));
        }

        throw new IllegalArgumentException("Before creating a Savings Account,a primary Account Holder needs to be created");

    }

    public Account createCreditCardAccount(AccountDTO accountDTO) throws Exception {
        if (accountHolderRepository.findById(accountDTO.getNewPrimaryOwner()).isPresent()) {
            AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getNewPrimaryOwner()).get();
            AccountHolder secondaryOwner = null;
            if (accountDTO.getNewSecondaryOwner() != null) {
                if (accountHolderRepository.findById(accountDTO.getNewSecondaryOwner()).isPresent()) {
                    secondaryOwner = accountHolderRepository.findById(accountDTO.getNewSecondaryOwner()).get();
                }
            }
            return accountRepository.save(new CreditCard (accountDTO.getNewBalance(),accountDTO.getNewSecretKey(),primaryOwner, secondaryOwner,accountDTO.getNewCreditLimit(),accountDTO.getNewInterestRate()));
        }
        throw new IllegalArgumentException("Before creating a CreditCard Account,a primary Account Holder needs to be created");
    }
}
