package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DefaultStudentService implements StudentService {
    Students students;

    public DefaultStudentService(Students students) {
        this.students = students;
    }

    @Override
    public Collection<Student> getPassedStudents() {
        return students.findAll().stream().filter(student -> Objects.nonNull(student.getScore()))
                .filter(student -> !student.getScore().isFail()).collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        return students.findAll().stream().filter(student -> Objects.nonNull(student.getScore()))
                .sorted((a, b) -> b.getScore().getScore() - a.getScore().getScore()).collect(Collectors.toList());
    }

}
