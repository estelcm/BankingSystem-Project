package com.ironhack.BankingSystem.controller;

import com.ironhack.BankingSystem.dto.TransactionDTO;
import com.ironhack.BankingSystem.model.transaction.Transaction;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.model.users.ThirdParty;
import com.ironhack.BankingSystem.service.AccountHolderService;
import com.ironhack.BankingSystem.service.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountHolderService accountHolderService;

    @Autowired
    ThirdPartyService thirdPartyService;

    @PostMapping("/create_third_party_transaction")
    @ResponseStatus(HttpStatus.CREATED);
    public ThirdParty ThirdPartyTransaction (@RequestBody TransactionDTO transactionDTO){
        return thirdPartyService.createThirdPartyTransaction(transactionDTO);
    }


}
