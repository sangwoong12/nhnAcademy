package com.nhnacademy.mybatistodo.mapper;


import com.nhnacademy.mybatistodo.item.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

@Mapper
public interface EventMapper {
    long addEvent(Event event);

    //###조회 response : Event
    Event getEventById(@Param("id") long id);

    //###삭제 response : empty
    boolean deleteEventById(@Param("userId") String userId, @Param("id") long id);

    //###삭제 response : empty
    boolean deleteEventByEventAt(@Param("userId") String userId, @Param("eventAt") String eventAt);

    //##일일등록 카운트 response : count
    int getCountOfEventAt(@Param("userId") String userId, @Param("eventAt") String eventAt);

    //##월별조회 response : List<Event>
    List<Event> getEventListOfYearMonth(@Param("userId") String userId, @Param("yearmonth") Date yearmonth);

    //##일별 조회 response : List<Event>
    List<Event> getEventListByEventAt(@Param("userId") String userId, @Param("eventAt") Date eventAt);
}
