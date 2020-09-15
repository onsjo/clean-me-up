package com.effcode.clean.me.rest.config;

import com.effcode.clean.me.support.SmtpHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfiguration {

    @Bean
    public SmtpHandler smtpHandler() {
        return new SmtpHandler();
    }

}
