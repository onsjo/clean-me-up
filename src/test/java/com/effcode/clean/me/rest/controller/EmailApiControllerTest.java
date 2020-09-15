package com.effcode.clean.me.rest.controller;

import com.effcode.clean.me.rest.dto.EmailModel;
import com.effcode.clean.me.rest.service.EmailHandler;
import com.effcode.clean.me.rest.service.ValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmailApiController.class)
class EmailApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ValidationService validationService;
    @MockBean
    private EmailHandler emailHandler;

    @Test
    void sendEmptyBodyEmail() throws Exception {
        mockMvc.perform(post("/")
                .contentType("application/json"))
                .andExpect(status().isBadRequest());

    }

    @Test
    void sendEmail() throws Exception {
        EmailModel email = new EmailModel("my@test.se", "lala", "hej hej\n\npå dig!");

        mockMvc.perform(post("/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(email))
        ).andExpect(status().isNoContent());

    }

}