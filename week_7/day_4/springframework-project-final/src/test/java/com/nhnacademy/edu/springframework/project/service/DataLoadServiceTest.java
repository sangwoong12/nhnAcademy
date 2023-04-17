package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class DataLoadServiceTest {
    private final static Scores scores = new CsvScores();
    private final static Students students = new CsvStudents();
    private final static DataLoadService dataLoadService = new CsvDataLoadService(students, scores);


    @BeforeEach
    public void setUp() {
        dataLoadService.loadAndMerge();
    }

    @Test
    void loadAndMerge() {

        Assertions.assertEquals(4, students.findAll().size());
        Assertions.assertEquals(3, scores.findAll().size());

        List<Student> studentList = new ArrayList<>(students.findAll());
        //3번 까지는 score 가 존재 Not NULL 이 발생해야함.
        for (int i = 0; i < 3; i++) {
            assertNotNull(studentList.get(i).getScore());
        }
        //4번은 score 가 존재하지 않아 NULL 이 발생해야함.
        assertNull(studentList.get(3).getScore());
    }
}