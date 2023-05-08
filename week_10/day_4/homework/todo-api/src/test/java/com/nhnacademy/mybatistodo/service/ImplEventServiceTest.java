package service;

import com.nhnacademy.mybatistodo.exception.NotFoundEventException;
import com.nhnacademy.mybatistodo.exception.NotFoundEventOfEventAtException;
import com.nhnacademy.mybatistodo.item.Event;
import com.nhnacademy.mybatistodo.repository.EventRepository;
import com.nhnacademy.mybatistodo.service.EventService;
import com.nhnacademy.mybatistodo.service.ImplEventService;
import com.nhnacademy.mybatistodo.share.UserIdStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ImplEventServiceTest {

    private EventService eventService;

    private EventRepository eventRepository;

    @BeforeEach
    void setup() {
        UserIdStore.setUserId("marco");
        eventRepository = mock(EventRepository.class);
        eventService = new ImplEventService(eventRepository);
    }

    @Test
    void addEventTest() {
        Event event = new Event("marco", "test", LocalDate.of(2023, 4, 20));
        event.setId(1);
        when(eventRepository.addEvent(event)).thenReturn(1L);
        long l = eventService.addEvent(event);
        Assertions.assertEquals(1L, l);
    }

    @Test
    @DisplayName("getEventIfIdTest : event 가 존재할때")
    void getEventByIdTest() {
        Event event = new Event("marco", "test", LocalDate.of(2023, 4, 20));
        event.setId(1);
        when(eventRepository.getEventById(1)).thenReturn(event);
        Event eventById = eventService.getEventById(1);
        Assertions.assertEquals(eventById, event);
    }

    @Test
    @DisplayName("getEventIfIdTest : event 가 존재하지 않을 때")
    void getEventByIdTest2() {
        when(eventRepository.getEventById(anyInt())).thenReturn(null);

        Assertions.assertThrows(NotFoundEventException.class, () -> eventService.getEventById(1));
    }

    @Test
    @DisplayName("deleteEventOfId : event 가 존재하지 않아 예외 발생 확인.")
    void deleteEventByIdTest() {
        when(eventRepository.deleteEventById(anyString(), anyInt())).thenReturn(false);

        Assertions.assertThrows(NotFoundEventOfEventAtException.class, () -> eventService.deleteEventById(1));
    }

    @Test
    @DisplayName("deleteEventOfId : event 가 존재하지 않아 예외 발생 확인.")
    void deleteEventByEventAtTest() {
        when(eventRepository.deleteEventById(UserIdStore.getUserId(), 1)).thenReturn(false);

        Assertions.assertThrows(NotFoundEventOfEventAtException.class, () -> eventService.deleteEventById(1));
    }

    @Test
    @DisplayName("getCountByEventAtTest")
    void getCountByEventAtTest() {
        when(eventRepository.getCountOfEventAt(anyString(), anyString())).thenReturn(1);
        int countByEventAt = eventService.getCountByEventAt("2023-05-01");
        Assertions.assertEquals(countByEventAt, 1);
    }

    @Test
    @DisplayName("getEventListByYearMonthTest")
    void getEventListByYearMonthTest() {
        List<Event> events = new ArrayList<>();
        Event event = new Event("marco", "test", LocalDate.of(2023, 4, 20));
        events.add(event);
        when(eventRepository.getEventListOfYearMonth(anyString(), anyString(), anyString())).thenReturn(events);
        List<Event> eventListByYearMonth = eventService.getEventListByYearMonth("2023", "04");
        Assertions.assertEquals(eventListByYearMonth, events);
    }

    @Test
    @DisplayName("getEventListByEventAtTest")
    void getEventListByEventAtTest() {
        List<Event> events = new ArrayList<>();
        Event event = new Event("marco", "test", LocalDate.of(2023, 4, 20));
        events.add(event);
        when(eventRepository.getEventListByEventAt(anyString(), anyString())).thenReturn(events);
        List<Event> eventListByEventAt = eventService.getEventListByEventAt("2023", "04", "20");
        Assertions.assertEquals(eventListByEventAt, events);
    }
}
