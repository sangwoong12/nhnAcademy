import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springmvcstudent.config.RootConfig;
import com.nhnacademy.springmvcstudent.config.WebConfig;
import com.nhnacademy.springmvcstudent.exception.DuplicateIdException;
import com.nhnacademy.springmvcstudent.item.Gender;
import com.nhnacademy.springmvcstudent.item.StudentRequest;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class) //JUnit 5에서 Spring Framework 기능을 사용하기 위해 필요한 어노테이션
@WebAppConfiguration //웹 애플리케이션 테스트할때 필요, 붙은 클래스는 웹 환경에서 실행되는 애플리케이션 컨텍스트 생성
@ContextHierarchy(value = { //여러개의 컨텍스트 설정 클래스를 지정
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
public class StudentRestControllerTest {
    //WebAppConfiguration 이 넣어줌.
    @Autowired
    WebApplicationContext context;

    ObjectMapper objectMapper = new ObjectMapper();

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }

    @Test
    void registerStudent() throws Exception {
        StudentRequest studentRequest = new StudentRequest();
        ReflectionTestUtils.setField(studentRequest, "id", "admin");
        ReflectionTestUtils.setField(studentRequest, "name", "관리자");
        ReflectionTestUtils.setField(studentRequest, "age", 30);
        ReflectionTestUtils.setField(studentRequest, "gender", Gender.M);

        mockMvc.perform(post("/api/students").content(objectMapper.writeValueAsString(studentRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @DisplayName("학생등록-나이=50")
    void registerStudent_max_age_exception() throws Exception {
        StudentRequest studentRequest = new StudentRequest();
        ReflectionTestUtils.setField(studentRequest, "id", "admin");
        ReflectionTestUtils.setField(studentRequest, "name", "관리자");
        ReflectionTestUtils.setField(studentRequest, "age", 50);
        ReflectionTestUtils.setField(studentRequest, "gender", Gender.M);
        MvcResult mvcResult = mockMvc.perform(post("/api/students").content(objectMapper.writeValueAsString(studentRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        String message = mvcResult.getResolvedException().getMessage();
    }

    @Test
    @DisplayName("학생등록_아이디중복")
    void registerStudent_exist_id() throws Exception {
        StudentRequest studentRequest = new StudentRequest();
        ReflectionTestUtils.setField(studentRequest, "id", "Student-1");
        ReflectionTestUtils.setField(studentRequest, "name", "마르코");
        ReflectionTestUtils.setField(studentRequest, "age", 30);
        ReflectionTestUtils.setField(studentRequest, "gender", Gender.M);

        MvcResult mvcResult = mockMvc.perform(post("/api/students").content(objectMapper.writeValueAsString(studentRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict())
                .andReturn();
        assertEquals(DuplicateIdException.class, mvcResult.getResolvedException().getClass());

    }

    @Test
    @DisplayName("학생조회")
    void getStudent() throws Exception {
        mockMvc.perform(get("/api/students/Student-1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("Student-1"))
                .andExpect(jsonPath("$.name").value("name-1"))
                .andExpect(jsonPath("$.gender").value("F"))
                .andExpect(jsonPath("$.age").isNumber())
                .andReturn();
    }

    @Test
    @DisplayName("학생수정")
    void updateStudent() throws Exception {
        StudentRequest studentRequest = new StudentRequest();
        ReflectionTestUtils.setField(studentRequest, "id", "Student-1");
        ReflectionTestUtils.setField(studentRequest, "name", "마르코");
        ReflectionTestUtils.setField(studentRequest, "gender", Gender.M);
        ReflectionTestUtils.setField(studentRequest, "age", 20);

        mockMvc.perform(put("/api/students/student1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(studentRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

}
