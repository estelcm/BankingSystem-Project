package com.ironhack.BankingSystem.service;


import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderService {


    @Autowired
    AccountRepository accountRepository;

    public AccountHolder createAccountHolder;



}
