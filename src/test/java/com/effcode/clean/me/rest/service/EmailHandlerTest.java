package com.effcode.clean.me.rest.service;

import com.effcode.clean.me.rest.dto.EmailModel;
import com.effcode.clean.me.support.SmtpEmail;
import com.effcode.clean.me.support.SmtpHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class EmailHandlerTest {

    @Mock
    private SmtpHandler smtpHandler;

    @InjectMocks
    private EmailHandler emailHandler;

    @Test
    void sendBasicEmail() {
        Mockito.doNothing().when(smtpHandler).post(any(SmtpEmail.class));

        String address = "some@adreess.com";
        String subject = "testing my subject";
        String content = "the content \n\n bye!";

        emailHandler.send(new EmailModel(address, subject, content));

        verify(smtpHandler, times(1)).post(any(SmtpEmail.class));
    }
}