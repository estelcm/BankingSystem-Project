package com.ironhack.BankingSystem.controllerTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.BankingSystem.model.Accounts.Account;
import com.ironhack.BankingSystem.model.Accounts.Checking;
import com.ironhack.BankingSystem.model.Accounts.CreditCard;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.model.users.Address;
import com.ironhack.BankingSystem.repository.AccountHolderRepository;
import com.ironhack.BankingSystem.repository.AccountRepository;
import com.ironhack.BankingSystem.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;


@SpringBootTest
public class AccountHolderTest {

    @Autowired
    WebApplicationContext context;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    TransactionRepository transactionRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    AccountHolder accountHolder1;
    AccountHolder accountHolder2;
    AccountHolder accountHolder3;

    Account originAcc;
    Account targetAcc;

    Checking checkingAcc1;
    Checking checkingAcc2;

    CreditCard creditCard;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        //first address bcs in order to create an accountHolder yu'll need address data
        Address address1 = new Address("Colon","12b", "Masnou","280426","Spain");
        Address address2 = new Address("Pamplona","71", "Barcelona","08018","Spain");
        Address address3 = new Address("Overhoeksplein ","1", "Amsterdam","1031","Países Bajos");
        accountHolder1 = new AccountHolder("Ironhack1", LocalDate.of(1998,6,3),address1,null);
        accountHolder1 = new AccountHolder("Iornhack2", LocalDate.of(2000,25,9),address2,null);
        accountHolder1 = new AccountHolder("Ironhack3", LocalDate.of(1990,17,10),address3,null);
    }
    //TODO test create account holder
    // TODO test checkbalance
    // TODO create transaction
}
