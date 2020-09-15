package com.effcode.clean.me.rest.service;

import com.effcode.clean.me.rest.dto.EmailModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.effcode.clean.me.support.SmtpEmail;
import com.effcode.clean.me.support.SmtpHandler;

@Component
public class EmailHandler {

    private static final Logger LOG = LoggerFactory.getLogger(EmailHandler.class);

    private final SmtpHandler smtpHandler;


    @Value("app.email.username")
    private String emailUsername;

    @Value("app.email.password")
    private String emailPassword;


    public EmailHandler(SmtpHandler smtpHandler) {
        this.smtpHandler = smtpHandler;
    }


    public void send(EmailModel email) {
        if( LOG.isDebugEnabled() ){
            LOG.debug("Adr: {}, Subject: {}, Content: {}", email.getAddress(), email.getSubject(), email.getContent());
        }

        smtpHandler.post(convertToSmptEmail(email));

        LOG.info("Send email. Adr: {}, Subject: {}", email.getAddress(), email.getSubject());
    }


    private SmtpEmail convertToSmptEmail(EmailModel email) {
        SmtpEmail smtpEmail = new SmtpEmail();
        smtpEmail.adrs = new String[] {email.getAddress()};
        smtpEmail.content = email.getContent();
        smtpEmail.subject = email.getSubject();
        smtpEmail.username = emailUsername;
        smtpEmail.password =  emailPassword;
        return smtpEmail;
    }


}
