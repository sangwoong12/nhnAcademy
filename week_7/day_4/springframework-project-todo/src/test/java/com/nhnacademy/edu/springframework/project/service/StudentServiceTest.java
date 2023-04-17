package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class StudentServiceTest {
    private final static StudentService service = new DefaultStudentService();

    @BeforeAll
    public static void setUpBeforeClass() {
        ReflectionTestUtils.setField(CsvScores.class, "scoreList", new ArrayList<>());
        ReflectionTestUtils.setField(CsvStudents.class, "studentList", new ArrayList<>());
        ReflectionTestUtils.setField(CsvScores.class, "path", Objects.requireNonNull(CsvScores.class.getResource("/")).getPath());
        ReflectionTestUtils.setField(CsvStudents.class, "path", Objects.requireNonNull(CsvScores.class.getResource("/")).getPath());

        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();
    }

    @Test
    void getPassedStudents() {
        List<Student> students = new ArrayList<>(service.getPassedStudents());
        //모두 패스한 학생인지 확인.
        Assertions.assertTrue(students.stream().noneMatch(student -> student.getScore().isFail()));
    }

    @Test
    void getStudentsOrderByScore() {
        List<Student> students = new ArrayList<>(service.getStudentsOrderByScore());
        List<Integer> scores = students.stream().map(student -> student.getScore().getScore()).collect(Collectors.toList());
        //내림차순으로 정렬되었는지 확인.
        Assertions.assertTrue(IntStream.range(1, students.size()).allMatch(i -> scores.get(i - 1) >= scores.get(i)));
    }
}