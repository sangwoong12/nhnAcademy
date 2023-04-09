package com.nhnacademy.student.listener;

import com.nhnacademy.student.item.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@WebListener
public class WebApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new JsonStudentRepository();
        Random random = new Random();
        for (int i=1; i<=10; i++) {
            Student student = Student.builder()
                    .age(20 + random.nextInt(10))
                    .id("Student"+i)
                    .name("name" + i)
                    .gender(i % 2 == 0 ? Gender.M : Gender.F)
                    .createAt(LocalDateTime.now())
                    .build();
            studentRepository.save(student);
            log.error("추가완료");
        }
        context.setAttribute("studentRepository",studentRepository);
    }
}
