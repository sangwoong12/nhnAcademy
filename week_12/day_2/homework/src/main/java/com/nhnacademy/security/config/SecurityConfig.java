package com.nhnacademy.security.config;

import com.nhnacademy.security.handler.CustomLoginSuccessHandler;
import com.nhnacademy.security.handler.CustomOauthLoginSuccessHandler;
import com.nhnacademy.security.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .requestMatchers("/admin").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers("/home").authenticated()
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
                    .successHandler(customLoginSuccessHandler())
                    .and()
                .oauth2Login()
                    .clientRegistrationRepository(clientRegistrationRepository())
                    .authorizedClientService(authorizedClientService())
                    .successHandler(customOauthLoginSuccessHandler())
                    .loginPage("/login")
                    .and()
                .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("SESSION")
                    .logoutSuccessUrl("/redirect-index")
                    .and()
                .csrf()
                    .disable()
                .cors()
                .and()
                .authorizeHttpRequests()
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
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler(){
        return new CustomLoginSuccessHandler();
    }
    @Bean
    public CustomOauthLoginSuccessHandler customOauthLoginSuccessHandler(){
        return new CustomOauthLoginSuccessHandler();
    }
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(github());
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

    private ClientRegistration github() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .userNameAttributeName("name")
                .clientId("6094f22e032a7dffd01b")
                .clientSecret("7aaeff3df2d390da6af9153e9bd7ad3868f843d9")
                .build();
    }
}
