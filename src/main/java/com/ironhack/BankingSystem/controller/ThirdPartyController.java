package com.ironhack.BankingSystem.controller;

import com.ironhack.BankingSystem.dto.TransactionDTO;
import com.ironhack.BankingSystem.dto.TransactionThirdDTO;
import com.ironhack.BankingSystem.model.transaction.Transaction;
import com.ironhack.BankingSystem.service.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ThirdPartyController {

    @Autowired
    ThirdPartyService thirdPartyService;



    @PostMapping("/create_third_party_transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction ThirdPartyTransaction (@RequestBody TransactionThirdDTO transactionthirdDTO, @RequestHeader("Hashed-key")String hashedKey) throws Exception {

        return thirdPartyService.createThirdPartyTransaction(transactionthirdDTO, hashedKey);

    }
}
