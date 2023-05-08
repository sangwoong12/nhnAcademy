package com.nhnacademy.mybatistodo.service;

import com.nhnacademy.mybatistodo.item.Event;

import java.util.List;

public interface EventService {
    long addEvent(Event event);

    Event getEventById(long id);

    void deleteEventById(long id);

    void deleteEventByEventAt(String eventAt);

    int getCountByEventAt(String eventAt);

    List<Event> getEventListByYearMonth(String year, String month);

    List<Event> getEventListByEventAt(String year, String month, String day);
}
