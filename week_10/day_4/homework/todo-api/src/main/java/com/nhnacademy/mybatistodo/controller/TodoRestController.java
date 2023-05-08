package com.nhnacademy.mybatistodo.controller;

import com.nhnacademy.mybatistodo.domain.CountResponse;
import com.nhnacademy.mybatistodo.domain.EventAddRequest;
import com.nhnacademy.mybatistodo.domain.EventAddResponse;
import com.nhnacademy.mybatistodo.exception.InvalidParameterException;
import com.nhnacademy.mybatistodo.item.Event;
import com.nhnacademy.mybatistodo.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nhnacademy.mybatistodo.item.Event.createEventOfEventAddRequest;

@RestController
@RequestMapping("/api/calendar")
public class TodoRestController {
    EventService eventService;

    public TodoRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/events")
    public ResponseEntity<EventAddResponse> addEvent(@RequestBody EventAddRequest eventAddRequest, @ModelAttribute String userId) {
        Event event = createEventOfEventAddRequest(eventAddRequest);
        long id = eventService.addEvent(event);
        EventAddResponse eventAddResponse = new EventAddResponse(id);
        return ResponseEntity.status(201).body(eventAddResponse);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventOfId(@PathVariable("id") long id, @ModelAttribute String userId) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEventOfId(@PathVariable("id") String id, @ModelAttribute String userId) {
        eventService.deleteEventById(Long.parseLong(id));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/events/daily/{eventAt}")
    public ResponseEntity<Void> deleteEventOfEventId(@PathVariable("eventAt") String eventAt, @ModelAttribute String userId) {
        eventService.deleteEventByEventAt(eventAt);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/daily-register-count")
    public ResponseEntity<CountResponse> getCountOfDate(@RequestParam("date") String date, @ModelAttribute String userId) {
        int count = eventService.getCountByEventAt(date);
        return ResponseEntity.status(HttpStatus.OK).body(new CountResponse(count));
    }

    //무조건 온다는 보장이 없음.
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEvents(
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String day) {
        if (year == null) {
            throw new InvalidParameterException("year");
        }
        if (month == null) {
            throw new InvalidParameterException("month");
        }
        List<Event> events = day == null ? eventService.getEventListByYearMonth(year, month) : eventService.getEventListByEventAt(year, month, day);
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }
}
