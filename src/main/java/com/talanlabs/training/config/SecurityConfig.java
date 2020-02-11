package com.talanlabs.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
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

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password(passwordEncoder().encode("password")).roles("USER").build());
        manager.createUser(User.withUsername("journalist").password(passwordEncoder().encode("curious")).roles("WRITER").build());
        manager.createUser(User.withUsername("redactor").password(passwordEncoder().encode("chief")).roles("PUBLISHER").build());
        return manager;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}