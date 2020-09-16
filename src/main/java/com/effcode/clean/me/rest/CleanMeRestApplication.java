package com.effcode.clean.me.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.effcode.clean.me.support.SmtpHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class CleanMeRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanMeRestApplication.class, args);
	}
}
