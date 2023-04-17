package com.nhnacademy.edu.springframework;

import com.nhn.dooray.client.DoorayHook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DoorayHookSender {
    private RestTemplate restTemplate;
    @Value("${hookUrl}")
    private String url;

    @Autowired
    public DoorayHookSender(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void send(DoorayHook doorayHook) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<DoorayHook> entity = new HttpEntity(doorayHook, headers);
        this.restTemplate.exchange(this.url, HttpMethod.POST, entity, String.class, new Object[0]);
    }
}