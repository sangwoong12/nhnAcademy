package service;

import com.example.todoapi.exception.NotFoundEventException;
import com.example.todoapi.exception.NotFoundEventOfEventAtException;
import com.example.todoapi.item.Event;
import com.example.todoapi.repository.EventRepository;
import com.example.todoapi.service.EventService;
import com.example.todoapi.service.ImplEventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ImplEventServiceTest {

    private EventService eventService;

    private EventRepository eventRepository;
    @BeforeEach
    void setup() {
        eventRepository = mock(EventRepository.class);
        eventService = new ImplEventService(eventRepository);
    }
    @Test
    void addEventTest(){
        Event event = new Event("test", "2023-04-20");
        event.setId(1);
        when(eventRepository.addEvent(event)).thenReturn(1L);
        long l = eventService.addEvent(event);
        Assertions.assertEquals(1L,l);
    }
    @Test
    @DisplayName("getEventIfIdTest : event 가 존재할때")
    void getEventByIdTest(){
        Event event = new Event("test", "2023-04-20");
        event.setId(1);
        when(eventRepository.getEventById(1)).thenReturn(event);
        Event eventById = eventService.getEventById(1);
        Assertions.assertEquals(eventById,event);
    }
    @Test
    @DisplayName("getEventIfIdTest : event 가 존재하지 않을 때")
    void getEventByIdTest2(){
        when(eventRepository.getEventById(anyInt())).thenReturn(null);

        Assertions.assertThrows(NotFoundEventException.class, () -> eventService.getEventById(1));
    }
    @Test
    @DisplayName("deleteEventOfId : event 가 존재하지 않아 예외 발생 확인.")
    void deleteEventByIdTest(){
        when(eventRepository.deleteEventById(anyInt())).thenReturn(false);

        Assertions.assertThrows(NotFoundEventOfEventAtException.class, () -> eventService.deleteEventById(1));
    }
    @Test
    @DisplayName("deleteEventOfId : event 가 존재하지 않아 예외 발생 확인.")
    void deleteEventByEventAtTest(){
        when(eventRepository.deleteEventById(1)).thenReturn(false);

        Assertions.assertThrows(NotFoundEventOfEventAtException.class, () -> eventService.deleteEventById(1));
    }
    @Test
    @DisplayName("getCountByEventAtTest")
    void getCountByEventAtTest(){
        when(eventRepository.getCountOfEventAt(anyString())).thenReturn(1);
        int countByEventAt = eventService.getCountByEventAt(anyString());
        Assertions.assertEquals(countByEventAt,1);
    }
    @Test
    @DisplayName("getEventListByYearMonthTest")
    void getEventListByYearMonthTest(){
        List<Event> events = new ArrayList<>();
        Event event = new Event("test", "2023-04-20");
        events.add(event);
        when(eventRepository.getEventListOfYearMonth(anyString())).thenReturn(events);
        List<Event> eventListByYearMonth = eventService.getEventListByYearMonth("2023", "04");
        Assertions.assertEquals(eventListByYearMonth,events);
    }
    @Test
    @DisplayName("getEventListByEventAtTest")
    void getEventListByEventAtTest(){
        List<Event> events = new ArrayList<>();
        Event event = new Event("test", "2023-04-20");
        events.add(event);
        when(eventRepository.getEventListByEventAt(anyString())).thenReturn(events);
        List<Event> eventListByEventAt = eventService.getEventListByEventAt("2023", "04", "20");
        Assertions.assertEquals(eventListByEventAt,events);
    }
}
