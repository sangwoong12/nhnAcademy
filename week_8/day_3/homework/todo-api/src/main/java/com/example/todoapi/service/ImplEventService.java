package com.example.todoapi.service;

import com.example.todoapi.exception.NotFoundEventException;
import com.example.todoapi.exception.NotFoundEventOfEventAtException;
import com.example.todoapi.item.Event;
import com.example.todoapi.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ImplEventService implements EventService {
    EventRepository eventRepository;

    public ImplEventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public long addEvent(Event event) {
        return eventRepository.addEvent(event);
    }

    public Event getEventById(long id) {
        Event event = eventRepository.getEventById(id);
        if (Objects.isNull(event)) {
            throw new NotFoundEventException(id);
        }
        return event;
    }

    public void deleteEventById(long id) {
        if (!eventRepository.deleteEventById(id)) {
            throw new NotFoundEventOfEventAtException();
        }
    }

    public void deleteEventByEventAt(String eventAt) {
        if (!eventRepository.deleteEventByEventAt(eventAt)) {
            throw new NotFoundEventOfEventAtException();
        }
    }

    public int getCountByEventAt(String eventAt) {
        return eventRepository.getCountOfEventAt(eventAt);
    }

    public List<Event> getEventListByYearMonth(String year, String month) {
        String yearMonth = year + "-" + month;
        return eventRepository.getEventListOfYearMonth(yearMonth);
    }

    public List<Event> getEventListByEventAt(String year, String month, String day) {
        String eventAt = year + "-" + month + "-" + day;
        return eventRepository.getEventListByEventAt(eventAt);
    }
}
