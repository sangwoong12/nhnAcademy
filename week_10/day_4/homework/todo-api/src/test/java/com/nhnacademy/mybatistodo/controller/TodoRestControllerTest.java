package controller;

import com.nhnacademy.mybatistodo.controller.TodoRestController;
import com.nhnacademy.mybatistodo.domain.EventAddRequest;
import com.nhnacademy.mybatistodo.exception.AccessDeniedException;
import com.nhnacademy.mybatistodo.exception.InvalidParameterException;
import com.nhnacademy.mybatistodo.exception.UnauthorizedUserException;
import com.nhnacademy.mybatistodo.item.Event;
import com.nhnacademy.mybatistodo.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

class TodoRestControllerTest {
    private MockMvc mockMvc;
    private EventService eventService;
    ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setup() {
        eventService = mock(EventService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new TodoRestController(eventService))
                .build();
    }

    @Test
    @DisplayName("X-USER-ID null 일때 UnauthorizedUserException 발생여부 확인.")
    void modelAttributeTest() throws Exception {
        try {
            mockMvc.perform(get("/api/calendar/events/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
        } catch (NestedServletException e) {
            //then
            assertTrue(e.getCause() instanceof UnauthorizedUserException);
            assertEquals("Unauthorized", e.getCause().getMessage());
        }
    }

    @Test
    @DisplayName("X-USER-ID abc 일때 AccessDeniedException 발생여부 확인.")
    void modelAttributeTest2() throws Exception {
        try {
            mockMvc.perform(get("/api/calendar/events/1")
                            .header("X-USER-ID", "abc")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
        } catch (NestedServletException e) {
            //then
            assertEquals(true, e.getCause() instanceof AccessDeniedException);
            assertEquals("잘못된 이벤트 소유자", e.getCause().getMessage());
        }
    }

    @Test
    @DisplayName("add event : 추가 이후 event에 해당하는 id 를 반환 여부 확인.")
    void addEventTest() throws Exception {
        //given
        EventAddRequest eventAddRequest = new EventAddRequest();
        ReflectionTestUtils.setField(eventAddRequest, "subject", "test");
        ReflectionTestUtils.setField(eventAddRequest, "eventAt", "2023-04-20");

        //when
        when(eventService.addEvent(any())).thenReturn(1L);
        mockMvc.perform(post("/api/calendar/events")
                        .content(objectMapper.writeValueAsString(eventAddRequest))
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(xpath("/EventAddResponse/id").string("1"))
                .andReturn();
    }

    @Test
    @DisplayName("get event : id 로 조회후 데이터 확인.")
    void getEventOfIdTest() throws Exception {
        //given
        Event event = new Event("marco","test", LocalDate.of(2023,4,20));
        event.setId(1);

        //when
        when(eventService.getEventById(1)).thenReturn(event);

        mockMvc.perform(get("/api/calendar/events/{id}", "1")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(xpath("/Event/id").string("1"))
                .andExpect(xpath("/Event/eventAt").string("2023-04-20"))
                .andExpect(xpath("/Event/subject").string("test"))
                .andReturn();
    }

    @Test
    @DisplayName("delete Id : return 200 확인 사실상 처리로직이 없어 200만 확인")
    void deleteEventOfIdTest() throws Exception {
        //given
        long id = 1;
        //when
        mockMvc.perform(delete("/api/calendar/events/{id}", id)
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("delete eventAt : return 200 확인 사실상 처리로직이 없어 200만 확인")
    void deleteEventOfEventIdTest() throws Exception {
        //given
        String eventAt = "2023-04-20";
        //when
        mockMvc.perform(delete("/api/calendar/events/daily/{eventAt}", eventAt)
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("get count : CountResponse 에 값이 잘 담기는지 확인")
    void getCountOfDateTest() throws Exception {
        //given
        int count = 10;
        //when
        when(eventService.getCountByEventAt("2023-04-20")).thenReturn(count);
        mockMvc.perform(get("/api/calendar/daily-register-count")
                        .header("X-USER-ID", "marco")
                        .param("date", "2023-04-20")
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(xpath("/CountResponse/count").string("10"))
                .andReturn();
    }

    @Test
    @DisplayName("get events : year null 일경우 InvalidParameterException 발생 여부 확인")
    void getEventsTest() throws Exception {
        //given
        String month = "02";
        //when
        try {
            mockMvc.perform(get("/api/calendar/events")
                            .header("X-USER-ID", "marco")
                            .param("month", month)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
        } catch (NestedServletException e) {
            //then
            assertTrue(e.getCause() instanceof InvalidParameterException);
            assertEquals("Required request parameter year for method parameter type Integer is not present", e.getCause().getMessage());
        }
    }

    @Test
    @DisplayName("get events : month null 일경우 InvalidParameterException 발생 여부 확인")
    void getEventsTest2() throws Exception {
        //given
        String year = "2023";
        //when
        try {
            mockMvc.perform(get("/api/calendar/events")
                            .header("X-USER-ID", "marco")
                            .param("year", year)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
        } catch (NestedServletException e) {
            //then
            assertTrue(e.getCause() instanceof InvalidParameterException);
            assertEquals("Required request parameter month for method parameter type Integer is not present", e.getCause().getMessage());
        }
    }

    @Test
    @DisplayName("get events : year, month 주고 결과 확인")
    void getEventsTest3() throws Exception {
        //day 여부에 따른 결과 차이는 service 에서 처리를 하기 때문에 따로 테스트 하지 않음.
        //given
        String year = "2023";
        String month = "04";
        List<Event> events = new ArrayList<>();
        Event event = new Event("marco","test", LocalDate.of(2023,4,20));
        event.setId(1);
        events.add(event);

        //when
        when(eventService.getEventListByYearMonth(year, month)).thenReturn(events);

        mockMvc.perform(get("/api/calendar/events")
                        .header("X-USER-ID", "marco")
                        .param("year", year)
                        .param("month", month)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(xpath("List/item/id").string("1"))
                .andExpect(xpath("List/item/eventAt").string("2023-04-20"))
                .andExpect(xpath("List/item/subject").string("test"))
                .andReturn();

    }
}
