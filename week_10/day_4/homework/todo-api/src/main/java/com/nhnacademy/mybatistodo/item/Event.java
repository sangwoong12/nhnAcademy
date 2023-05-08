package com.nhnacademy.mybatistodo.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhnacademy.mybatistodo.domain.EventAddRequest;
import com.nhnacademy.mybatistodo.share.UserIdStore;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class Event {
    private long id;
    private String subject;

    private String userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate eventAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public Event(String userId, String subject, LocalDate eventAt) {
        this.userId = userId;
        this.subject = subject;
        this.eventAt = eventAt;
        this.createdAt = LocalDateTime.now();
    }

    public Event(long id, String subject, String userId, LocalDate eventAt, LocalDateTime createdAt) {
        this.id = id;
        this.subject = subject;
        this.userId = userId;
        this.eventAt = eventAt;
        this.createdAt = createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static Event createEventOfEventAddRequest(EventAddRequest eventAddRequest) {
        return new Event(UserIdStore.getUserId(), eventAddRequest.getSubject(), LocalDate.parse(eventAddRequest.getEventAt(), DateTimeFormatter.ISO_DATE));
    }

}
