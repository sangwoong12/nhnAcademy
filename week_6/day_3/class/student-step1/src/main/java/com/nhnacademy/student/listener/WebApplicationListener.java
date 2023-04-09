package com.nhnacademy.student.listener;

import com.nhnacademy.student.item.Gender;
import com.nhnacademy.student.item.MapStudentRepository;
import com.nhnacademy.student.item.Student;
import com.nhnacademy.student.item.StudentRepository;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;
import java.util.Random;

@WebListener
public class WebApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        StudentRepository studentRepository = new MapStudentRepository();
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
        }
        sce.getServletContext().setAttribute("studentRepository",studentRepository);
    }
}
