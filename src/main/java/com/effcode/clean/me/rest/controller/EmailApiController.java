package com.effcode.clean.me.rest.controller;

import com.effcode.clean.me.rest.dto.EmailModel;
import com.effcode.clean.me.rest.exceptions.EmailValidationException;
import com.effcode.clean.me.rest.service.EmailHandlerService;
import com.effcode.clean.me.rest.service.ValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/email")
public class EmailApiController {

    private static final Logger LOG = LoggerFactory.getLogger(EmailApiController.class);

    private final EmailHandlerService emailHandler;
    private final ValidationService validationService;

    public EmailApiController(EmailHandlerService emailHandler, ValidationService validationService) {
        this.emailHandler = emailHandler;
        this.validationService = validationService;
    }


    @PostMapping(value = "/send", consumes = "application/json")
    @PreAuthorize("hasRole('MAIL_SENDER')")
    @Operation(summary = "Sends an email through our ancient legacy system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The email is sent successfully."),
            @ApiResponse(responseCode = "400", description = "You did something wrong."),
            @ApiResponse(responseCode = "500", description = "We did something wrong.")
    })
    public ResponseEntity<Void> send(@RequestBody EmailModel email, HttpServletRequest request) {
        MDC.put("remoteIp", request.getRemoteAddr());
        LOG.info("Processing email send requests.");

        try {
            validationService.validateEmail(email);
        } catch (EmailValidationException e) {
            LOG.error("Invalid email data provided: {}", e.getMessage());
            MDC.clear();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        MDC.put("emailAddress", email.getAddress());
        MDC.put("emailSubject", email.getSubject());

        LOG.info("Sending email.");
        emailHandler.send(email);

        MDC.clear();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
