package com.ironhack.BankingSystem.service;


import com.ironhack.BankingSystem.dto.TransactionDTO;
import com.ironhack.BankingSystem.model.Accounts.Account;
import com.ironhack.BankingSystem.model.Accounts.Checking;
import com.ironhack.BankingSystem.model.Accounts.Savings;
import com.ironhack.BankingSystem.model.transaction.Transaction;
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
public class ThirdPartyService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public Transaction createThirdPartyTransaction (TransactionDTO transactionDTO){
        Account targetAccount = accountRepository.findById(transactionDTO.getTargetAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Target Account not found"));
        AccountHolder targetAccountOwner = accountHolderRepository.findByName(transactionDTO.getTargetAccountHolderName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Target Account Holder not found"));
        Transaction transaction;
        Account originAccount;
        Long accountId = transactionDTO.getOriginAccountId();
        BigDecimal balance;

        if (accountRepository.findByIdAndPrimaryOwnerName(accountId, targetAccountOwner).isPresent()) {
            originAccount = accountRepository.findByIdAndPrimaryOwnerName(accountId, targetAccountOwner).get();
            balance = originAccount.getBalance();

            if (originAccount instanceof Checking) {

                if (balance.compareTo(((Checking) originAccount).getMinimumBalance()) < 0) {
                    originAccount.setBalance(originAccount.getBalance().subtract(originAccount.getPenaltyFee()));
                    accountRepository.save(originAccount);
                }
            }
            if (originAccount instanceof Savings) {

                if (balance.compareTo(((Savings) originAccount).getMinimumBalance()) < 0) {
                    originAccount.setBalance(originAccount.getBalance().subtract(originAccount.getPenaltyFee()));
                    accountRepository.save(originAccount);
                }
            }
            if (balance.compareTo(transactionDTO.getAmount()) == 1) {
                originAccount.setBalance(originAccount.getBalance().subtract(transactionDTO.getAmount()));
                targetAccount.setBalance(targetAccount.getBalance().add(transactionDTO.getAmount()));
                accountRepository.save(originAccount);
                accountRepository.save(targetAccount);
                transaction = new Transaction(transactionDTO.getTargetAccountHolderName(),transactionDTO.getAmount(),  originAccount, targetAccount);
                return transactionRepository.save(transaction);

            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Not enough funds");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sending Account not found");
        }
    }
    }

