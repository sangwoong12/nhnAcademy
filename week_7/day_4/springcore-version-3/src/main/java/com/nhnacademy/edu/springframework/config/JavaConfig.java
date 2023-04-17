package com.nhnacademy.edu.springframework.config;

import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = {"com.nhnacademy.edu.springframework"})
@PropertySource("classpath:item.properties")
@EnableAspectJAutoProxy
public class JavaConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
