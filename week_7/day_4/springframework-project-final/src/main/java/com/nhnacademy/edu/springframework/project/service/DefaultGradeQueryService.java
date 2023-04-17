package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DefaultGradeQueryService implements GradeQueryService {
    Students students;

    public DefaultGradeQueryService(Students students) {
        this.students = students;
    }

    @Override
    public List<Score> getScoreByStudentName(String name) {
        return students.findAll().stream().filter(student -> Objects.nonNull(student.getScore()))
                .filter(student -> student.getName().equals(name)).map(Student::getScore).collect(Collectors.toList());
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        return students.findAll().stream().map(Student::getScore)
                .filter(studentScore -> studentScore.getStudentSeq() == seq).findAny().orElse(null);
    }
}
