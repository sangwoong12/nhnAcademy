package com.nhnacademy.mybatistodo.service;

import com.nhnacademy.mybatistodo.exception.AccessDeniedException;
import com.nhnacademy.mybatistodo.exception.NotFoundEventException;
import com.nhnacademy.mybatistodo.exception.NotFoundEventOfEventAtException;
import com.nhnacademy.mybatistodo.item.Event;
import com.nhnacademy.mybatistodo.repository.EventRepository;
import com.nhnacademy.mybatistodo.share.UserIdStore;
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
        event.setUserId(UserIdStore.getUserId());

        return eventRepository.addEvent(event);
    }

    public Event getEventById(long id) {
        Event event = eventRepository.getEventById(id);
        if (Objects.isNull(event)) {
            throw new NotFoundEventException(id);
        }
        if (!event.getUserId().equals(UserIdStore.getUserId())) {
            throw new AccessDeniedException();
        }
        return event;
    }

    public void deleteEventById(long id) {
        if (!eventRepository.deleteEventById(UserIdStore.getUserId(), id)) {
            throw new NotFoundEventOfEventAtException();
        }
    }

    public void deleteEventByEventAt(String eventAt) {
        if (!eventRepository.deleteEventByEventAt(UserIdStore.getUserId(), eventAt)) {
            throw new NotFoundEventOfEventAtException();
        }
    }

    public int getCountByEventAt(String eventAt) {
        return eventRepository.getCountOfEventAt(UserIdStore.getUserId(), eventAt);
    }

    public List<Event> getEventListByYearMonth(String year, String month) {
        return eventRepository.getEventListOfYearMonth(UserIdStore.getUserId(), year, month);
    }

    public List<Event> getEventListByEventAt(String year, String month, String day) {
        String eventAt = year + "-" + month + "-" + day;
        return eventRepository.getEventListByEventAt(UserIdStore.getUserId(), eventAt);
    }
}
