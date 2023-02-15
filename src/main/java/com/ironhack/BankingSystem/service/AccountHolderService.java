package com.ironhack.BankingSystem.service;
import com.ironhack.BankingSystem.model.Accounts.CreditCard;
import com.ironhack.BankingSystem.model.transaction.Transaction;

import com.ironhack.BankingSystem.dto.AccountHolderDTO;
import com.ironhack.BankingSystem.dto.TransactionDTO;
import com.ironhack.BankingSystem.model.Accounts.Account;
import com.ironhack.BankingSystem.model.Accounts.Checking;
import com.ironhack.BankingSystem.model.Accounts.Savings;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.repository.AccountHolderRepository;
import com.ironhack.BankingSystem.repository.AccountRepository;
import com.ironhack.BankingSystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AccountHolderService {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;


    @Autowired
    TransactionRepository transactionRepository;


    public AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO) {
        AccountHolder accountHolder = new AccountHolder(accountHolderDTO.getNewName(), accountHolderDTO.getNewDateOfBirth(), accountHolderDTO.getNewPrimaryAddress(), accountHolderDTO.getNewMailingAddress());
        return accountHolderRepository.save(accountHolder);
    }


    public BigDecimal checkBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        return account.getBalance();
    }

    public Transaction createTransaction(TransactionDTO transactionDTO) {

        Account targetAccount = accountRepository.findById(transactionDTO.getTargetAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Account not found"));

        AccountHolder targetAccountHolder = accountHolderRepository.findByName(transactionDTO.getTargetAccountHolderName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Target Account Holder not found"));

        Account originAccount;
        Long accountId = transactionDTO.getOriginAccountId();
        Transaction transaction;

        if (accountRepository.findById(accountId).isPresent()) {
            originAccount = accountRepository.findById(accountId).get();

            if (originAccount.getBalance().compareTo(transactionDTO.getAmount()) > 0) {
                originAccount.setBalance(originAccount.getBalance().subtract(transactionDTO.getAmount()));
                targetAccount.setBalance(targetAccount.getBalance().add(transactionDTO.getAmount()));
                accountRepository.save(originAccount);
                accountRepository.save(targetAccount);

                transaction = new Transaction(transactionDTO.getTargetAccountHolderName(), transactionDTO.getAmount(), originAccount, targetAccount);
                transactionRepository.save(transaction);

                if (originAccount instanceof Checking) {
                    if (originAccount.getBalance().compareTo(((Checking) originAccount).getMinimumBalance()) < 0) ;
                    originAccount.setBalance(originAccount.getBalance().subtract(originAccount.getPenaltyFee()));
                    accountRepository.save(originAccount);
                }


                if (originAccount instanceof Savings) {
                    Savings savingsAccount = (Savings) originAccount;
                    savingsAccount.applyInterestRate();
                    if (savingsAccount.getBalance().compareTo(((Savings) savingsAccount).getMinimumBalance()) < 0) {
                        savingsAccount.setBalance(savingsAccount.getBalance().subtract(savingsAccount.getPenaltyFee()));
                        accountRepository.save(savingsAccount);
                    }
                }

                if(originAccount instanceof CreditCard){
                    CreditCard creditCardAccount= (CreditCard) originAccount;
                    creditCardAccount.applyInterestRate();
                }


            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough funds");
            }





        }


        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Origin account not found");
        }
        return transaction;

    }
}
