package com.ironhack.BankingSystem.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class ThirdPartyTest {
    @Autowired
    WebApplicationContext context;
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    void thirdParty_makePositiveTransaction(){}

    @Test
    void thirdParty_makeNegativeTransaction(){}


}
