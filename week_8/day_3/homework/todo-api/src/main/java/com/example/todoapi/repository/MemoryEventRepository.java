package com.example.todoapi.repository;

import com.example.todoapi.item.Event;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class MemoryEventRepository implements EventRepository {
    // event 저장을 위한 map 사용
    private final Map<String, List<Event>> eventMap = new ConcurrentHashMap<>();
    //id 채번을 위한 AtomicLong 사용
    private final AtomicLong autoIdx = new AtomicLong();

    //###등록 response : id
    public long addEvent(Event event) {
        long id = autoIdx.incrementAndGet();
        event.setId(id);

        if (eventMap.containsKey(event.getEventAt())) {
            List<Event> events = eventMap.get(event.getEventAt());
            events.add(event);
            return event.getId();
        }
        List<Event> list = new ArrayList<>();
        list.add(event);
        eventMap.put(event.getEventAt(), list);
        return id;
    }

    //###조회 response : Event
    public Event getEventById(long id) {
        return eventMap.values().stream()
                .flatMap(Collection::stream)
                .filter(event -> event.getId() == id)
                .findFirst()
                .orElse(null);
    }

    //###삭제 response : empty
    public boolean deleteEventById(long id) {
        for (List<Event> value : eventMap.values()) {
            for (Event event : value) {
                if (event.getId() == id) {
                    value.remove(event);
                    return true;
                }
            }
        }
        return false;
    }


    //###삭제 response : empty
    public boolean deleteEventByEventAt(String eventAt) {
        if (eventMap.containsKey(eventAt)) {
            eventMap.remove(eventAt);
            return true;
        }
        return false;
    }

    //##일일등록 카운트 response : count
    public int getCountOfEventAt(String eventAt) {
        return eventMap.containsKey(eventAt) ? eventMap.get(eventAt).size() : 0;
    }

    //##월별조회 response : List<Event>
    public List<Event> getEventListOfYearMonth(String yearmonth) {
        List<Event> list = new ArrayList<>();
        for (String key : eventMap.keySet()) {
            if (key.contains(yearmonth)) {
                list.addAll(eventMap.get(key));
            }
        }
        return list;
    }

    //##일별 조회 response : List<Event>
    public List<Event> getEventListByEventAt(String eventAt) {
        if (Objects.isNull(eventMap.get(eventAt))) {
            return new ArrayList<>();
        }
        return eventMap.get(eventAt);
    }
}
