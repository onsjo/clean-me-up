package com.effcode.clean.me.rest.service;

import com.effcode.clean.me.rest.dto.EmailModel;
import com.effcode.clean.me.rest.exceptions.EmailValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class ValidationService {

    private static final Logger LOG = LoggerFactory.getLogger(ValidationService.class);

    public void validateEmail(final EmailModel email) throws EmailValidationException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<EmailModel>> violations = validator.validate(email);

        if (!violations.isEmpty()) throw new EmailValidationException(violations);
    }

}
