package com.ironhack.BankingSystem.service;


import com.ironhack.BankingSystem.dto.ThirdPartyDTO;
import com.ironhack.BankingSystem.dto.TransactionDTO;
import com.ironhack.BankingSystem.dto.TransactionThirdDTO;
import com.ironhack.BankingSystem.model.Accounts.Account;
import com.ironhack.BankingSystem.model.Accounts.Checking;
import com.ironhack.BankingSystem.model.Accounts.Savings;
import com.ironhack.BankingSystem.model.transaction.Transaction;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.model.users.ThirdParty;
import com.ironhack.BankingSystem.repository.AccountHolderRepository;
import com.ironhack.BankingSystem.repository.AccountRepository;
import com.ironhack.BankingSystem.repository.ThirdPartyRepository;
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
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    public Transaction createThirdPartyTransaction (TransactionThirdDTO transactionThirdDTO, String hashedKey) throws Exception{
        ThirdParty thirdPartyKey = thirdPartyRepository.findByHashedKey(hashedKey).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"hashed key not found"));
        Account involvedAccount = accountRepository.findById(transactionThirdDTO.getInvolvedAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Target Account not found"));

        if ( !involvedAccount.getSecretKey().equals(transactionThirdDTO.getSecretKey())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Secret key is not right");
        }


    //involvedAccount.setBalance(involvedAccount.getBalance().add(transactionDTO.getAmount());

        if (involvedAccount.getBalance().add(transactionThirdDTO.getAmount()).compareTo(involvedAccount.getBalance()) < 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Not enough funds");
        }

        involvedAccount.setBalance(involvedAccount.getBalance().add(transactionThirdDTO.getAmount()));

        accountRepository.save(involvedAccount);
        Transaction transaction = new Transaction(involvedAccount.getPrimaryOwner().getName(), transactionThirdDTO.getAmount(), null, null);

        transactionRepository.save(transaction);

        return transaction;
    }


    }


