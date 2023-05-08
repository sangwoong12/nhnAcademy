package com.nhnacademy.mybatistodo.domain;

public class EventAddRequest {
    String subject;
    String eventAt;

    public EventAddRequest() {
    }

    public String getSubject() {
        return subject;
    }

    public String getEventAt() {
        return eventAt;
    }
}
