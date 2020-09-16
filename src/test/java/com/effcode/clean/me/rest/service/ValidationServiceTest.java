package com.effcode.clean.me.rest.service;

import com.effcode.clean.me.rest.dto.EmailModel;
import com.effcode.clean.me.rest.exceptions.EmailValidationException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ValidationServiceTest {

    @InjectMocks
    private ValidationService validationService;


    @Test
    void validateEmailWithEmptyEmailAddress() {
        EmailModel email = new EmailModel("", "email subject", "lite content");

        Exception exception = assertThrows(EmailValidationException.class, () -> {
            validationService.validateEmail(email);
        });

        String expectedMessage = "Email must not be empty.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void validateEmailWithInvalidEmailAddress() {
        EmailModel email = new EmailModel("lala.test", "email subject", "lite content");

        Exception exception = assertThrows(EmailValidationException.class, () -> {
            validationService.validateEmail(email);
        });

        String expectedMessage = "Email was invalid.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void validateEmailWithEmptySubject() {
        EmailModel email = new EmailModel("a@real.mail", "", "lite content");

        Exception exception = assertThrows(EmailValidationException.class, () -> {
            validationService.validateEmail(email);
        });

        String expectedMessage = "Subject must not be empty.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void validateEmailWithLongSubject() {
        String longSubject = StringUtils.repeat("A", 79);

        EmailModel email = new EmailModel("lala@test.se", longSubject, "lite content");

        Exception exception = assertThrows(EmailValidationException.class, () -> {
            validationService.validateEmail(email);
        });

        String expectedMessage = "Subject must be below 78 characters.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void validateEmail() throws EmailValidationException {
        EmailModel email = new EmailModel("lala@test.se", "hej", "lite content");

        validationService.validateEmail(email);
    }

}