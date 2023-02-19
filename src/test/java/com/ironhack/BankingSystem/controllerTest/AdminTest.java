package com.ironhack.BankingSystem.controllerTest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.BankingSystem.dto.AccountDTO;
import com.ironhack.BankingSystem.dto.AccountHolderDTO;
import com.ironhack.BankingSystem.dto.ThirdPartyDTO;
import com.ironhack.BankingSystem.dto.TransactionDTO;
import com.ironhack.BankingSystem.model.Accounts.*;
import com.ironhack.BankingSystem.model.Accounts.Enums.Status;
import com.ironhack.BankingSystem.model.transaction.Transaction;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.model.users.Address;
import com.ironhack.BankingSystem.repository.AccountHolderRepository;
import com.ironhack.BankingSystem.repository.AccountRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.RequestEntity.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminTest {

    @Autowired
    WebApplicationContext context;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;


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

        checkingAcc1= new Checking(new BigDecimal("15.000"),"1111jj",accountHolder1,accountHolder2);
        checkingAcc2= new Checking(new BigDecimal("10.000"),"2222jj",accountHolder3,accountHolder4);
        accountRepository.saveAll(List.of(checkingAcc1,checkingAcc2));
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }
    @Test
    void createAccountHolder() throws Exception {
        //en el body se escriben los datos en json
        AccountHolderDTO accountHolderTest =new AccountHolderDTO("Antonio",LocalDate.of(1990,5,30),address1,null);
        //convierte obj java a json
        String body = objectMapper.writeValueAsString(accountHolderTest);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/create_account_holder")
                .content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Antonio"));
    }
    @Test
    void createCheckingAcc() throws Exception {

       AccountDTO accTest= new AccountDTO(new BigDecimal("5000"),"abcd",1,2,null,null,LocalDate.of(2022,10,10),LocalDate.of(2022,12,30));
        String body = objectMapper.writeValueAsString(accTest);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/create_checking_account")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("abcd"));

    }

    @Test
    void updateAccountWhenNeeded() throws Exception {
      AccountDTO accTest= new AccountDTO(null,"mmmm",null,null,null,null,null,null);

        String body = objectMapper.writeValueAsString(accTest);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/update_account/" + checkingAcc2.getAccountId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andReturn();
        assertEquals("mmmm", accountRepository.findById(checkingAcc2.getAccountId()).get().getSecretKey());




    }

    @Test
    void createThirdPartyInvolved() throws Exception {

        ThirdPartyDTO thirdPartyDTO= new ThirdPartyDTO("TPV","asdfgh");
        String body= objectMapper.writeValueAsString(thirdPartyDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/create_third_party")
                .content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("TPV"));




    }

    @Test
    void AdminCan_deleteAcc() throws Exception {

        MvcResult mvcResult = mockMvc.perform(delete("/delete_account/" + checkingAcc1.getAccountId()))
                .andExpect(status().isAccepted()).andReturn();
       assertFalse(accountRepository.findById(checkingAcc1.getAccountId()).isPresent());



    }

    //NOT WORKING! because I have one method that creates all accounts, and this restriction is in the setter(studentChecking). I don't really get how to check if I'm getting the studentChecking....
    @Test
    void createChecking_ifLessThan24Years() throws Exception {
        AccountHolder HolderLess24= accountHolderRepository.save(new AccountHolder("youndAdult",LocalDate.of(2005,10,7),address1, null));

        AccountDTO accTest= new AccountDTO(new BigDecimal("5000"),"abcd",1,2,null,null,LocalDate.of(2022,10,10),LocalDate.of(2022,12,30));
        String body = objectMapper.writeValueAsString(accTest);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/create_checking_account")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated()).andReturn();


      assertTrue(StudentChecking.class.isAssignableFrom(accTest.getClass()));



    }

}
