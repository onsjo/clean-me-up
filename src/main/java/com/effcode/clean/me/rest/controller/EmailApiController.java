package com.effcode.clean.me.rest.controller;

import com.effcode.clean.me.rest.dto.EmailModel;
import com.effcode.clean.me.rest.exceptions.EmailValidationException;
import com.effcode.clean.me.rest.service.EmailHandler;
import com.effcode.clean.me.rest.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class EmailApiController {

    private static final Logger LOG = LoggerFactory.getLogger(EmailApiController.class);

    private final EmailHandler emailHandler;
    private final ValidationService validationService;

    public EmailApiController(EmailHandler emailHandler, ValidationService validationService) {
        this.emailHandler = emailHandler;
        this.validationService = validationService;
    }


    @PostMapping("/")
    public ResponseEntity<Void> send(@RequestBody EmailModel email) {
        try {
            validationService.validateEmail(email);
        } catch (EmailValidationException e) {
            LOG.error("Invalid email data provided.", e);
            MDC.clear();
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        MDC.put("emailAddress", email.getAddress());
        MDC.put("emailSubject", email.getSubject());

        emailHandler.send(email);
        MDC.clear();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
