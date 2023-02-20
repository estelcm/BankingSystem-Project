package com.ironhack.BankingSystem.service;


import com.ironhack.BankingSystem.controller.AccountController;
import com.ironhack.BankingSystem.controller.AdminController;
import com.ironhack.BankingSystem.dto.AccountDTO;
import com.ironhack.BankingSystem.dto.AccountHolderDTO;
import com.ironhack.BankingSystem.dto.ThirdPartyDTO;
import com.ironhack.BankingSystem.model.Accounts.*;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.model.users.ThirdParty;
import com.ironhack.BankingSystem.model.users.User;
import com.ironhack.BankingSystem.repository.*;
import com.ironhack.BankingSystem.service.impl.UserService;
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
    UserService userService;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;


    /*
    When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account should be created otherwise a regular Checking Account should be created.
     */

    public User createAccountHolder(AccountHolderDTO accountHolderDTO) {
        User accountHolder = new AccountHolder(accountHolderDTO.getNewName(), accountHolderDTO.getNewUserName(), accountHolderDTO.getNewPassword(), accountHolderDTO.getNewDateOfBirth(), accountHolderDTO.getNewPrimaryAddress(), accountHolderDTO.getNewMailingAddress());
       userService.saveUser(accountHolder);
       userService.addRoleToUser(accountHolder.getUsername(),"ROLE_ACCOUNTHOLDER");
        return userService.saveUser(accountHolder);
    }
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

    public Account updateAccount (AccountDTO accountDTO, Long accountId) throws Exception {
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



        if (account instanceof  Checking){
            Checking checkingAccount= (Checking) account;
            if(checkingAccount.getStatus() != null){
                checkingAccount.setStatus(checkingAccount.getStatus());
            }
        }

        if (account instanceof StudentChecking){
            StudentChecking studentCheckingAccount = (StudentChecking) account;
            if  (studentCheckingAccount.getStatus() != null){
               studentCheckingAccount.setStatus(studentCheckingAccount.getStatus());
            }
        }
        if (account instanceof Savings) {
            Savings savingsAccount = (Savings) account;
            if (accountDTO.getNewInterestRate() != null)
                savingsAccount.setInterestRate(accountDTO.getNewInterestRate());
            if (savingsAccount.getStatus() != null) {
                savingsAccount.setStatus(savingsAccount.getStatus());
            }
        }

        if (account instanceof CreditCard) {
            CreditCard creditCardAccount= (CreditCard) account;
            if (accountDTO.getNewCreditLimit() != null)
                creditCardAccount.setCreditLimit(accountDTO.getNewCreditLimit());
            if (accountDTO.getNewInterestRate() != null)
                creditCardAccount.setInterestRate(accountDTO.getNewInterestRate());
        }
        return accountRepository.save(account);
    }

    public void deleteAccount (long accountId){
        accountRepository.delete(accountRepository.findById(accountId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found")));
    }
    public ThirdParty createThirdPartyUser (ThirdPartyDTO thirdPartyDTO){
        ThirdParty thirdParty = thirdPartyRepository.save(new ThirdParty(thirdPartyDTO.getNewThirdPartyName(),  thirdPartyDTO.getNewHashedKey()));
        return thirdParty;
    }

}

