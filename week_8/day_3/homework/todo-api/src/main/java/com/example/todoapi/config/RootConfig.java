package com.example.todoapi.config;

import com.example.todoapi.Base;
import com.example.todoapi.item.Event;
import com.example.todoapi.repository.EventRepository;
import com.example.todoapi.repository.MemoryEventRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {
    private String eventAt1 = "2023-04-19";
    private String eventAt2 = "2023-04-20";
    @Bean
    public EventRepository eventRepository() {
        EventRepository eventRepository = new MemoryEventRepository();
        eventRepository.addEvent(new Event("hello1", eventAt1));
        eventRepository.addEvent(new Event("hello2", eventAt1));
        eventRepository.addEvent(new Event("hello3", eventAt1));
        eventRepository.addEvent(new Event("hello4", eventAt1));
        eventRepository.addEvent(new Event("hello5", eventAt1));
        eventRepository.addEvent(new Event("hello6", eventAt2));
        eventRepository.addEvent(new Event("hello7", eventAt2));
        eventRepository.addEvent(new Event("hello8", eventAt2));
        eventRepository.addEvent(new Event("hello9", eventAt2));
        eventRepository.addEvent(new Event("hello10", eventAt2));
        return eventRepository;
    }
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}
