package com.effcode.clean.me.rest.controller;

import com.effcode.clean.me.rest.dto.EmailModel;
import com.effcode.clean.me.rest.service.EmailHandlerService;
import com.effcode.clean.me.rest.service.ValidationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
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
    private EmailHandlerService emailHandler;


    @Value("${app.auth.username}")
    private String username;
    @Value("${app.auth.password}")
    private String password;


    @Test
    void dontAllowUnauthorizedSending() throws Exception {
        mockMvc.perform(post("/email/send")
                .contentType("application/json")
                .content(getNewEmail())
        ).andExpect(status().isUnauthorized());
    }

    @Test
    void sendEmptyBodyEmail() throws Exception {
        mockMvc.perform(post("/email/send")
                .with(user(username).password(password))
                .contentType("application/json")
        ).andExpect(status().isBadRequest());
    }

    @Test
    void sendEmail() throws Exception {
        mockMvc.perform(post("/email/send")
                .with(user(username).password(password))
                .contentType("application/json")
                .content(getNewEmail())
        ).andExpect(status().isNoContent());

    }


    private String getNewEmail() {
        try {
            return objectMapper.writeValueAsString(new EmailModel("my@test.se", "lala", "hej hej\n\npå dig!"));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to create test email string.", e);
        }
    }

}