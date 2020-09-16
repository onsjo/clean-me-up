package com.effcode.clean.me.rest.exceptions;

import com.effcode.clean.me.rest.dto.EmailModel;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static java.util.stream.Collectors.joining;

public class EmailValidationException extends Exception {

    private final Set<ConstraintViolation<EmailModel>> violations;

    /**
     * @param violations all violations
     */
    public EmailValidationException(Set<ConstraintViolation<EmailModel>> violations) {
        this.violations = violations;
    }

    @Override
    public String getMessage() {
        return violations.stream().map(ConstraintViolation::getMessage).collect(joining(","));
    }
}
