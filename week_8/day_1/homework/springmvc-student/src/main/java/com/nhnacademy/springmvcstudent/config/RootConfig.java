package com.nhnacademy.springmvcstudent.config;

import com.nhnacademy.springmvcstudent.Base;
import com.nhnacademy.springmvcstudent.item.Gender;
import com.nhnacademy.springmvcstudent.item.Student;
import com.nhnacademy.springmvcstudent.item.User;
import com.nhnacademy.springmvcstudent.repository.StudentRepository;
import com.nhnacademy.springmvcstudent.repository.StudentRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Random;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public StudentRepository studentRepository() {
        StudentRepository studentRepository = new StudentRepositoryImpl();
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            Student student = new Student("Student-" + i, "name-" + i, i % 2 == 0 ? Gender.M : Gender.F, 20 + random.nextInt(10), LocalDateTime.now());
            studentRepository.save(student);
        }
        return studentRepository;
    }

    @Bean
    public User user() {
        return new User("admin", "관리자", "1234");
    }
}
