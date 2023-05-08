package com.nhnacademy.mybatistodo.mapper;

import com.nhnacademy.mybatistodo.config.RootConfig;
import com.nhnacademy.mybatistodo.item.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = RootConfig.class
)
@Transactional
class EventMapperTest {

    @Autowired
    EventMapper eventMapper;

    @Test
    void addEvent() {
        Event event = new Event("subject", "marco", LocalDate.now());

        long l = eventMapper.addEvent(event);

        assertThat(l).isEqualTo(1);
    }

    @Test
    void getEventById() {
        long id = 1;
        Event eventById = eventMapper.getEventById(id);

        assertThat(eventById.getEventAt()).isEqualTo("2023-05-01");
        assertThat(eventById.getId()).isEqualTo(1);
        assertThat(eventById.getSubject()).isEqualTo("test");
        assertThat(eventById.getUserId()).isEqualTo("marco");
    }

    @Test
    void deleteEventById() {
        long id = 1;
        boolean b = eventMapper.deleteEventById("marco", id);

        assertThat(b).isTrue();
    }

    @Test
    void deleteEventByEventAt() {
        boolean b = eventMapper.deleteEventByEventAt("marco", "2023-05-01");

        assertThat(b).isTrue();
    }

    @Test
    void getCountOfEventAt() {
        int b = eventMapper.getCountOfEventAt("marco", "2023-05-01");

        assertThat(b).isEqualTo(2);
    }

    @Test
    void getEventListOfYearMonth() {
        LocalDate date = LocalDate.of(2023, 5, 1);
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        List<Event> list = eventMapper.getEventListOfYearMonth("marco", sqlDate);

        assertThat(list).hasSize(5);
    }

    @Test
    void getEventListByEventAt() {
        LocalDate date = LocalDate.of(2023, 5, 1);
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        List<Event> list = eventMapper.getEventListByEventAt("marco", sqlDate);

        assertThat(list).hasSize(2);
    }
}