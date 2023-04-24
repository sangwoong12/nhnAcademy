package com.example.todoapi.item;

import com.example.todoapi.domain.EventAddRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Event {
    long id;
    String subject;
    String eventAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    LocalDateTime createAt;

    public Event(String subject, String eventAt) {
        this.subject = subject;
        this.eventAt = eventAt;
        this.createAt = LocalDateTime.now();
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getEventAt() {
        return eventAt;
    }

    public String getSubject() {
        return subject;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public static Event createEventOfEventAddRequest(EventAddRequest eventAddRequest) {
        return new Event(eventAddRequest.getSubject(), eventAddRequest.getEventAt());
    }

}
