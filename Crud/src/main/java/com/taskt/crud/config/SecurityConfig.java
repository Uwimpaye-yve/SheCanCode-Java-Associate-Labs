package com.taskt.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Allows us to use Postman without CSRF token issues
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/error").permitAll() // Public paths
                        .anyRequest().authenticated()                    // Everything else is locked down
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/api/users", true) // Redirects to our CRUD endpoint after logging in
                );

        return http.build();
    }
}