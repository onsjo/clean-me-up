package com.effcode.clean.me.rest.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    public boolean send(String adr, String subject, String content) {
        if( LOG.isDebugEnabled() ){
            LOG.debug("Adr: {}, Subject: {}, Content: {}", adr, subject, content);
        }

        if(subject == null) {
            LOG.error("Subject is null");
            return false;
        }
        if( content != null && content.length() > 65000) {
            LOG.error("Content to BIG: {}", content.length());
            return false;
        }

        SmtpEmail smtpEmail = createEmail(adr, subject, content);
        smtpHandler.post(smtpEmail);

        LOG.info("Send email. Adr: {}, Subject: {}", adr, subject);

        return true;
    }


    private SmtpEmail createEmail(String adr, String subject, String content) {
        SmtpEmail smtpEmail = new SmtpEmail();
        smtpEmail.adrs = new String[] {adr};
        smtpEmail.content = content;
        smtpEmail.subject = subject;
        smtpEmail.username = emailUsername;
        smtpEmail.password =  emailPassword;
        return smtpEmail;
    }


}
