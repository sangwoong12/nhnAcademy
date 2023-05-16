package com.nhnacademy.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .headers()
                    .defaultsDisabled()
                    .cacheControl()/*.disable()*/.and()
                    .contentTypeOptions()/*.disable()*/.and()
                    .frameOptions().sameOrigin()
                .and()
                .build();
    }
}
