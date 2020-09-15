package com.effcode.clean.me.rest.service;

import com.effcode.clean.me.rest.dto.EmailModel;
import com.effcode.clean.me.rest.exceptions.EmailValidationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidationService {

    public void validateEmail(final EmailModel email) throws EmailValidationException {
        if( email == null ){
            throw new IllegalArgumentException("Email was null.");
        }

        if( !isValidEmailAddress(email.getAddress()) ){
            throw new EmailValidationException("address");
        }

        // TODO: add validation below:
        /*
        if(subject == null) {
            LOG.error("Subject is null");
            return false;
        }
        if( content != null && content.length() > 65000) {
            LOG.error("Content to BIG: {}", content.length());
            return false;
        }
        */
    }

    private boolean isValidEmailAddress(String address) {
        if( StringUtils.isEmpty(address) ) return false;

        // TODO: add regex email address validation, or fancier?

        return true;
    }
}
