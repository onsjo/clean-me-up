package com.effcode.clean.me.rest.service;

import com.effcode.clean.me.rest.dto.EmailModel;
import com.effcode.clean.me.support.SmtpEmail;
import com.effcode.clean.me.support.SmtpHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailHandlerServiceTest {

    @Mock
    private SmtpHandler smtpHandler;

    @InjectMocks
    private EmailHandlerService emailHandlerService;

    @Test
    void sendBasicEmail() {
        Mockito.doNothing().when(smtpHandler).post(any(SmtpEmail.class));

        String address = "some@adreess.com";
        String subject = "testing my subject";
        String content = "the content \n\n bye!";

        emailHandlerService.send(new EmailModel(address, subject, content));

        verify(smtpHandler, times(1)).post(any(SmtpEmail.class));
    }
}