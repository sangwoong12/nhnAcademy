package com.nhnacademy.mybatistodo.repository;

import com.nhnacademy.mybatistodo.item.Event;

import java.util.List;

public interface EventRepository {
    long addEvent(Event event);

    //###조회 response : Event
    Event getEventById(long id);

    //###삭제 response : empty
    boolean deleteEventById(String userId, long id);

    //###삭제 response : empty
    boolean deleteEventByEventAt(String userId, String eventAt);

    //##일일등록 카운트 response : count
    int getCountOfEventAt(String userId, String eventAt);

    //##월별조회 response : List<Event>
    List<Event> getEventListOfYearMonth(String userId, String year, String month);

    //##일별 조회 response : List<Event>
    List<Event> getEventListByEventAt(String userId, String eventAt);
}
