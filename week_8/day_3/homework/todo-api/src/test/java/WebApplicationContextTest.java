import com.example.todoapi.advice.CommonControllerAdvice;
import com.example.todoapi.config.RootConfig;
import com.example.todoapi.config.WebConfig;
import com.example.todoapi.domain.EventAddRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(value = {
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class}),
})
class WebApplicationContextTest {

    @Autowired
    WebApplicationContext context;

    ObjectMapper objectMapper = new ObjectMapper();

    MockMvc mockMvc;

    @Autowired
    CommonControllerAdvice commonControllerAdvice;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }

    @Test
    @DisplayName("### 예외 400 year 값이 없을때")
    void err400ofYear() throws Exception {
        mockMvc.perform(get("/api/calendar/events")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("400"))
                .andExpect(jsonPath("$.message").value("Required request parameter year for method parameter type Integer is not present"))
                .andReturn();
    }

    @Test
    @DisplayName("### 예외 400 month 값이 없을때")
    void err400ofMonth() throws Exception {
        mockMvc.perform(get("/api/calendar/events?year=2023")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("400"))
                .andExpect(jsonPath("$.message").value("Required request parameter month for method parameter type Integer is not present"))
                .andReturn();
    }

    @Test
    @DisplayName("### 예외 401")
    void err401() throws Exception {
        mockMvc.perform(get("/api/calendar/events/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.statusCode").value("401"))
                .andExpect(jsonPath("$.message").value("Unauthorized"))
                .andReturn();
    }

    @Test
    @DisplayName("### 예외 403")
    void err403() throws Exception {
        mockMvc.perform(get("/api/calendar/events/1")
                        .header("X-USER-ID", "abd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.statusCode").value("403"))
                .andExpect(jsonPath("$.message").value("잘못된 이벤트 소유자"))
                .andReturn();
    }

    @Test
    @DisplayName("### 예외 404")
    void err404() throws Exception {
        mockMvc.perform(get("/api/calendar/events/1000000")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode").value("404"))
                .andExpect(jsonPath("$.message").value("이벤트가 존재하지 않습니다. eventId : 1000000"))
                .andReturn();
    }

    @Test
    @DisplayName("### 예외 405")
    void err405() throws Exception {
        mockMvc.perform(patch("/api/calendar/events/1")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(jsonPath("$.statusCode").value("405"))
                .andExpect(jsonPath("$.message").value("Request method 'PATCH' not supported"))
                .andReturn();
    }

    @Test
    @DisplayName("### 예외 500")
    void err500() throws Exception {
        mockMvc.perform(get("/api/calendar/events/a")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.statusCode").value("500"))
                .andExpect(jsonPath("$.message").value("Failed to convert value of type 'java.lang.String' to required type 'long'; nested exception is java.lang.NumberFormatException: For input string: \"a\""))
                .andReturn();
    }

    @Test
    @DisplayName("addEvent : 추가 성공")
    void addEventTest() throws Exception {
        EventAddRequest eventAddRequest = new EventAddRequest();
        ReflectionTestUtils.setField(eventAddRequest, "subject", "test");
        ReflectionTestUtils.setField(eventAddRequest, "eventAt", "2023-04-20");
        mockMvc.perform(post("/api/calendar/events")
                        .content(objectMapper.writeValueAsString(eventAddRequest))
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("11"))
                .andReturn();
    }

    @Test
    @DisplayName("getEventOfId : 받기 성공")
    void getEventOfIdTest() throws Exception {
        mockMvc.perform(get("/api/calendar/events/1")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subject").value("hello1"))//createAt 은 비교 불가.
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.eventAt").value("2023-04-19"))
                .andReturn();
    }

    @Test
    @DisplayName("이벤트 아이디로 삭제 성공")
    void deleteEventOfIdTest() throws Exception {
        mockMvc.perform(delete("/api/calendar/events/1")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("이벤트 아이디가 존재하지 않아 삭제 실패")
    void deleteEventOfIdTest2() throws Exception {
        mockMvc.perform(delete("/api/calendar/events/200000")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @DisplayName("이벤트 날짜로 삭제 성공")
    void deleteEventOfEventIdTest() throws Exception {
        mockMvc.perform(delete("/api/calendar/events/daily/2023-04-20")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("이벤트 날짜가 존재하지 않아 실패")
    void deleteEventOfEventIdTest2() throws Exception {
        mockMvc.perform(delete("/api/calendar/events/daily/2021-04-20")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @DisplayName("날짜 카운트")
    void getCountOfDateTest() throws Exception {
        mockMvc.perform(get("/api/calendar/daily-register-count")
                        .param("date", "2023-04-19")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("4"))
                .andReturn();
    }

    @Test
    @DisplayName("존재하지 않는 날짜 카운트")
    void getCountOfDateTest2() throws Exception {
        mockMvc.perform(get("/api/calendar/daily-register-count")
                        .param("date", "2020-04-19")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value("0"))
                .andReturn();
    }

    @Test
    @DisplayName("년 월 일 으로 조회 성공")
    void getEventsTest() throws Exception {
        mockMvc.perform(get("/api/calendar/events")
                        .param("year", "2023")
                        .param("month", "04")
                        .param("day", "19")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("년 월 일 으로 존재하지 않는 데이터 조회")
    void getEventsTest2() throws Exception {
        mockMvc.perform(get("/api/calendar/events")
                        .param("year", "2024")
                        .param("month", "04")
                        .param("day", "19")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("년 월 으로 조회 성공")
    void getEventsTest3() throws Exception {
        mockMvc.perform(get("/api/calendar/events")
                        .param("year", "2023")
                        .param("month", "04")
                        .header("X-USER-ID", "marco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
