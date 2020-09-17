package com.effcode.clean.me.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    // TODO: Replace this ugly Basic Auth with Oauth2
    @Value("${app.auth.username}")
    private String username;
    @Value("${app.auth.password}")
    private String password;
    @Value("${app.auth.username2}")
    private String username2;
    @Value("${app.auth.password2}")
    private String password2;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/swagger-ui.html", "/v3/api-docs/")
                .permitAll();

        http.authorizeRequests()
                .antMatchers("/**")
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .httpBasic();
        ;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        // TODO: Should be fetched from database at least
        auth.inMemoryAuthentication()
                .withUser(username)
                .password(encoder.encode(password))
                .roles("USER", "MAIL_SENDER")
                .and()
                .withUser(username2)
                .password(encoder.encode(password2))
                .roles("USER");
    }

}
