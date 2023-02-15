package com.ironhack.BankingSystem.controller;

import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountHolderService accountHolderService;



}
