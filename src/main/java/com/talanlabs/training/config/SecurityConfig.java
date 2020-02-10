package com.talanlabs.training.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
            .inMemoryAuthentication()
                .withUser("journalist")
                .password(encoder.encode("curious"))
                .roles("WRITER")
            .and()
                .withUser("redactor")
                .password(encoder.encode("chief"))
                .roles("WRITER", "PUBLISHER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/version").permitAll()
                .antMatchers(HttpMethod.GET, "/api/articles").permitAll()
                .antMatchers(HttpMethod.POST, "/api/articles").hasRole("WRITER")
                .antMatchers(HttpMethod.PUT, "/api/articles/**").hasRole("PUBLISHER")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable();

    }
}