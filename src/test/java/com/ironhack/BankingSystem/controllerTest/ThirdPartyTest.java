package com.ironhack.BankingSystem.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.BankingSystem.dto.TransactionDTO;
import com.ironhack.BankingSystem.dto.TransactionThirdDTO;
import com.ironhack.BankingSystem.model.Accounts.Account;
import com.ironhack.BankingSystem.model.Accounts.Checking;
import com.ironhack.BankingSystem.model.users.AccountHolder;
import com.ironhack.BankingSystem.model.users.Address;
import com.ironhack.BankingSystem.repository.AccountHolderRepository;
import com.ironhack.BankingSystem.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ThirdPartyTest {
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

    @BeforeEach
    void setUp(){

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

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



//not working
        @Test
        public void thirdParty_makeTransaction() throws Exception {

            TransactionThirdDTO thirdTransactionDto= new TransactionThirdDTO("x1x1x1x1",accountRepository.findAll().get(0).getAccountId(),new BigDecimal("100"),"secretkey");


            String body = objectMapper.writeValueAsString(thirdTransactionDto);

            mockMvc.perform(MockMvcRequestBuilders.post("/create_third_party_transaction")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(thirdTransactionDto)))
                    .andExpect(status().isCreated());



        }


}
