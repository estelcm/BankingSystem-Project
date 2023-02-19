package com.ironhack.BankingSystem.controllerTest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.BankingSystem.dto.AccountDTO;
import com.ironhack.BankingSystem.dto.AccountHolderDTO;
import com.ironhack.BankingSystem.dto.TransactionDTO;
import com.ironhack.BankingSystem.model.Accounts.*;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.model.users.Address;
import com.ironhack.BankingSystem.repository.AccountHolderRepository;
import com.ironhack.BankingSystem.repository.AccountRepository;
import com.ironhack.BankingSystem.repository.TransactionRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
    AccountHolder accountHolder4;

    Address address1;
    Address address2;
    Address address3;

    Checking checkingAcc1;
    Checking checkingAcc2;

    StudentChecking studentAcc1;

    CreditCard creditCard;

    Savings savings;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        //first address bcs in order to create an accountHolder yu'll need address data
        address1 = new Address("Colon","12b", "Masnou","280426","Spain");
        address2 = new Address("Pamplona","71", "Barcelona","08018","Spain");
        address3 = new Address("Overhoeksplein ","1", "Amsterdam","1031","Países Bajos");
        address3 = new Address("Address4 ","4", "city3","44444","Country4");

        accountHolder1 = new AccountHolder("Ironhack1", LocalDate.of(1998,6,3),address1,null);
        accountHolder2 = new AccountHolder("Iornhack2", LocalDate.of(2000,2,9),address2,null);
        accountHolder3 = new AccountHolder("Ironhack3", LocalDate.of(1990,11,10),address3,null);
        accountHolder4 = new AccountHolder("Ironhack4", LocalDate.of(1979,4,4),address3,null);
        accountHolderRepository.saveAll(List.of(accountHolder1, accountHolder2, accountHolder3, accountHolder4));

        checkingAcc1= new Checking(new BigDecimal("15000"),"1111jj",accountHolder1,accountHolder2);
        checkingAcc2= new Checking(new BigDecimal("10000"),"2222jj",accountHolder3,accountHolder4);
        accountRepository.saveAll(List.of(checkingAcc1,checkingAcc2));
    }

    @AfterEach
    void tearDown() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }


    @Test
    void AccHolderCheckBalance_whenIdNotFound_Error() throws Exception{


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/get_balance").param("accountId", "40000")).andExpect(status().isNotFound()).andReturn();
        //assertTrue(mvcResult.getResponse().getContentAsString().contains("15000"));

        System.out.println(mvcResult.getResolvedException());

    }

    @Test
    void AccHolder_checkBalance() throws Exception{

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/get_balance").param("accountId", checkingAcc1.getAccountId().toString())).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("15000"));

        //System.out.println(mvcResult.getResolvedException());
    }
    //TODO test create account holder
    // TODO test checkbalance
    // TODO create transaction

    @Test
    public void testCreateTransaction() throws Exception {
       //como le doy la info del idorigin acocunt

        TransactionDTO transactionDTO = new TransactionDTO("Ironhack1",new BigDecimal("500"),accountRepository.findAll().get(0).getAccountId(),accountRepository.findAll().get(1).getAccountId());


        String body = objectMapper.writeValueAsString(transactionDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/create_transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDTO)))
                .andExpect(status().isCreated());



    }
}
