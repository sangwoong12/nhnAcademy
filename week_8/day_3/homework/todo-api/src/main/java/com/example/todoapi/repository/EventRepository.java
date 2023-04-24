package com.example.todoapi.repository;

import com.example.todoapi.item.Event;

import java.util.List;

public interface EventRepository {
    long addEvent(Event event);

    //###조회 response : Event
    Event getEventById(long id);

    //###삭제 response : empty
    boolean deleteEventById(long id);

    //###삭제 response : empty
    boolean deleteEventByEventAt(String eventAt);

    //##일일등록 카운트 response : count
    int getCountOfEventAt(String eventAt);

    //##월별조회 response : List<Event>
    List<Event> getEventListOfYearMonth(String yearmonth);

    //##일별 조회 response : List<Event>
    List<Event> getEventListByEventAt(String eventAt);
}
