package repository;

import com.example.todoapi.item.Event;
import com.example.todoapi.repository.EventRepository;
import com.example.todoapi.repository.MemoryEventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

class MemoryEventRepositoryTest {
    EventRepository eventRepository = new MemoryEventRepository();
    Map<String,List<Event>> map;
    @BeforeEach
    void setup() {
        Event event1 = new Event("test1", "2023-04-20");
        event1.setId(1);
        Event event2 = new Event("test2", "2023-04-20");
        event2.setId(2);
        Event event3 = new Event("test3", "2023-04-21");
        event3.setId(3);
        Event event4 = new Event("test4", "2023-04-21");
        event4.setId(4);
        List<Event> eventList1 = new ArrayList<>();
        eventList1.add(event1);
        eventList1.add(event2);
        List<Event> eventList2 = new ArrayList<>();
        eventList2.add(event3);
        eventList2.add(event4);
        map = new HashMap<>();
        map.put("2023-04-20",eventList1);
        map.put("2023-04-21",eventList2);
        ReflectionTestUtils.setField(eventRepository, "eventMap", map);
        AtomicLong atomicLong = new AtomicLong();
        atomicLong.set(4);
        ReflectionTestUtils.setField(eventRepository, "autoIdx", atomicLong);
    }
    @Test
    void addEventTest(){
        Event event = new Event("test", "2000-01-01");
        long id = eventRepository.addEvent(event);
        Assertions.assertEquals(5,id);
    }
    @Test
    @DisplayName("getEventByIdTest : 존재하는 id")
    void getEventByIdTest(){
        Event eventById = eventRepository.getEventById(1);
        Assertions.assertEquals(eventById.getEventAt(),"2023-04-20");
        Assertions.assertEquals(eventById.getId(),1);
        Assertions.assertEquals(eventById.getSubject(),"test1");
    }
    @Test
    @DisplayName("getEventByIdTest : 존재하지 않는 id")
    void getEventByIdTest2(){
        Event eventById = eventRepository.getEventById(10000);
        Assertions.assertNull(eventById);
    }
    @Test
    @DisplayName("deleteEventById : 존재하는id")
    void deleteEventByIdTest1(){
        boolean b = eventRepository.deleteEventById(1);
        Assertions.assertTrue(b);
    }
    @Test
    @DisplayName("deleteEventById : 존재하지 않는 id")
    void deleteEventByIdTest2(){
        boolean b = eventRepository.deleteEventById(10000);
        Assertions.assertFalse(b);
    }
    @Test
    @DisplayName("deleteEventById : 존재하는 날짜")
    void deleteEventByEventAtTest1(){
        boolean b = eventRepository.deleteEventByEventAt("2023-04-20");
        Assertions.assertTrue(b);
    }
    @Test
    @DisplayName("deleteEventById : 존재하지 않는 날짜")
    void deleteEventByEventAtTest2(){
        boolean b = eventRepository.deleteEventByEventAt("2024-04-20");
        Assertions.assertFalse(b);
    }
    @Test
    @DisplayName("getCountOfEventAt : 존재하지 않는 날짜")
    void getCountOfEventAtTest1(){
        int countOfEventAt = eventRepository.getCountOfEventAt("2023-04-21");
        Assertions.assertEquals(countOfEventAt,2);
    }
    @Test
    @DisplayName("getCountOfEventAt : 존재하지 않는 날짜")
    void getCountOfEventAtTest2(){
        int countOfEventAt = eventRepository.getCountOfEventAt("2024-04-21");
        Assertions.assertEquals(countOfEventAt,0);
    }
    @Test
    void getEventListOfYearMonthTest(){
        List<Event> eventListOfYearMonth = eventRepository.getEventListOfYearMonth("2023-04");
        Assertions.assertEquals(eventListOfYearMonth
                ,map.values().stream().flatMap(Collection::stream).collect(Collectors.toList()));
    }
    @Test
    void getEventListByEventAtTest(){
        List<Event> eventListByEventAt = eventRepository.getEventListByEventAt("2023-04-21");
        Assertions.assertEquals(eventListByEventAt.get(0).getId(),3);
        Assertions.assertEquals(eventListByEventAt.get(1).getId(),4);
        Assertions.assertEquals(eventListByEventAt.get(0).getSubject(),"test3");
        Assertions.assertEquals(eventListByEventAt.get(1).getSubject(),"test4");
        Assertions.assertEquals(eventListByEventAt.get(0).getEventAt(),"2023-04-21");
        Assertions.assertEquals(eventListByEventAt.get(1).getEventAt(),"2023-04-21");
    }
}
