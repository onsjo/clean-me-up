package com.effcode.clean.me.rest.exceptions;

public class EmailValidationException extends Exception {

    private final String fieldName;

    /**
     * @param fieldName the field name in violation
     */
    public EmailValidationException(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String getMessage() {
        return "Invalid email validation for field: " + fieldName;
    }
}
