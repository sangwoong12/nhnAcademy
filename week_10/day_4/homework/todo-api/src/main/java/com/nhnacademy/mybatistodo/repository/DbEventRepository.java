package com.nhnacademy.mybatistodo.repository;

import com.nhnacademy.mybatistodo.item.Event;
import com.nhnacademy.mybatistodo.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
@Primary
public class DbEventRepository implements EventRepository {
    private final EventMapper eventMapper;

    @Override
    public long addEvent(Event event) {
        return eventMapper.addEvent(event);
    }
    @Override
    public Event getEventById(long id) {
        return eventMapper.getEventById(id);
    }

    @Override
    public boolean deleteEventById(String userId, long id) {
        return eventMapper.deleteEventById(userId, id);
    }

    @Override
    public boolean deleteEventByEventAt(String userId, String eventAt) {
        return eventMapper.deleteEventByEventAt(userId, eventAt);
    }

    @Override
    public int getCountOfEventAt(String userId, String eventAt) {
        return eventMapper.getCountOfEventAt(userId, eventAt);
    }

    @Override
    public List<Event> getEventListOfYearMonth(String userId, String year, String month) {
        LocalDate date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        return eventMapper.getEventListOfYearMonth(userId, sqlDate);
    }

    @Override
    public List<Event> getEventListByEventAt(String userId, String eventAt) {
        LocalDate date = LocalDate.parse(eventAt, DateTimeFormatter.ISO_DATE);
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);

        return eventMapper.getEventListByEventAt(userId, sqlDate);
    }
}
