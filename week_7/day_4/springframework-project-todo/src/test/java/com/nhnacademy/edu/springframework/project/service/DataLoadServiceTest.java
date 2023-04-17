package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class DataLoadServiceTest {
    private final static Scores scores = CsvScores.getInstance();
    private final static Students students = CsvStudents.getInstance();

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(CsvStudents.class, "studentList", new ArrayList<>());
        ReflectionTestUtils.setField(CsvScores.class, "scoreList", new ArrayList<>());
        ReflectionTestUtils.setField(scores, "path", Objects.requireNonNull(CsvScores.class.getResource("/")).getPath());
        ReflectionTestUtils.setField(students, "path", Objects.requireNonNull(CsvScores.class.getResource("/")).getPath());

    }

    @Test
    void loadAndMerge() {

        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();

        Assertions.assertEquals(4, students.findAll().size());
        Assertions.assertEquals(3, scores.findAll().size());

        List<Student> studentList = new ArrayList<>(students.findAll());
        //repository 테스트를 따로 하기때문에 score 알맞게 잘 들어갔는지만 확인.
        //3번 까지는 score 가 존재 Not NULL 이 발생해야함.
        for (int i = 0; i < 3; i++) {
            assertNotNull(studentList.get(i).getScore());
        }
        //4번은 score 가 존재하지 않아 NULL 이 발생해야함.
        assertNull(studentList.get(3).getScore());
    }
}