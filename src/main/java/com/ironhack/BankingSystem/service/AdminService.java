package com.ironhack.BankingSystem.service;


import com.ironhack.BankingSystem.controller.AccountController;
import com.ironhack.BankingSystem.controller.AdminController;
import com.ironhack.BankingSystem.dto.AccountDTO;
import com.ironhack.BankingSystem.model.Accounts.Account;
import com.ironhack.BankingSystem.model.Accounts.Checking;
import com.ironhack.BankingSystem.model.Accounts.StudentChecking;
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
    public Account createCheckingAccount (AccountDTO accountDTO){


       if(accountHolderRepository.findById(accountDTO.getNewPrimaryOwner()).isPresent()) {
           AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getNewPrimaryOwner()).get();
           AccountHolder secondaryOwner = null;

           if (accountDTO.getNewSecundaryOwner() != null) {
               if (accountHolderRepository.findById(accountDTO.getNewSecundaryOwner()).isPresent()) {
                   secondaryOwner = accountHolderRepository.findById(accountDTO.getNewSecundaryOwner()).get();
               }
           }


           if (Period.between(primaryOwner.getDateOfBirth(), LocalDate.now()).getYears() >= 24) {
               return accountRepository.save(new Checking(accountDTO.getNewBalance(), accountDTO.getNewSecretKey(), primaryOwner, null));

           } else {
               return accountRepository.save(new StudentChecking(accountDTO.getNewBalance(), accountDTO.getNewSecretKey(), primaryOwner, secondaryOwner));
           }
       }

      throw new IllegalArgumentException("Before creating the Account you need to select a primary Account Holder");
    }


}
