package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentServiceTest {

    private final static Scores scores = new CsvScores();
    private final static Students students = new CsvStudents();
    private final static DataLoadService dataLoadService = new CsvDataLoadService(students, scores);
    private final static StudentService service = new DefaultStudentService(students);

    @BeforeAll
    public static void setUpClass() {
        dataLoadService.loadAndMerge();
    }

    @Test
    void getPassedStudents() {
        List<Student> students = new ArrayList<>(service.getPassedStudents());
        //모두 패스한 학생인지 확인.
        assertTrue(students.stream().noneMatch(student -> student.getScore().isFail()));
    }

    @Test
    void getStudentsOrderByScore() {
        List<Student> students = new ArrayList<>(service.getStudentsOrderByScore());
        List<Integer> scores = students.stream().map(student -> student.getScore().getScore()).collect(Collectors.toList());

        //내림차순으로 정렬되었는지 확인.
        assertTrue(IntStream.range(1, students.size()).allMatch(i -> scores.get(i - 1) >= scores.get(i)));
    }
}