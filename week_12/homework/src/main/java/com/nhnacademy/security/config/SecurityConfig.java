package com.nhnacademy.security.config;

import com.nhnacademy.security.handler.CustomLoginSuccessHandler;
import com.nhnacademy.security.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .requestMatchers("/home/**").authenticated()
                .requestMatchers("/home/admin").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers("/home/member").hasAnyAuthority("ROLE_MEMBER")
                .anyRequest().permitAll()
                    .and()
                .headers()
                    .defaultsDisabled()
                    .cacheControl()
                        .and()
                    .contentTypeOptions()
                        .and()
                    .frameOptions().sameOrigin()
                    .xssProtection().block(true)
                        .and()
                    .httpStrictTransportSecurity()
                        .includeSubDomains(true)
                        .maxAgeInSeconds(31536000)
                        .and()
                    .and()
                .formLogin()
                    .usernameParameter("id")
                    .passwordParameter("pwd")
                    .loginPage("/login")
                    .successHandler(new CustomLoginSuccessHandler())
                    .and()
                .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("SESSION")
                    .logoutSuccessUrl("/")
                    .and()
                .csrf()
                    .and()
                .sessionManagement()
                    .sessionFixation()
                        .none()
                    .and()
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
