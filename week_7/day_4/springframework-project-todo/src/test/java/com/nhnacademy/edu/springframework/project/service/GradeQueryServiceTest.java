package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GradeQueryServiceTest {

    private final static GradeQueryService service = new DefaultGradeQueryService();

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
    void getScoreByStudentName() {
        List<Score> scores = service.getScoreByStudentName("A");
        //동명이인 처리 테스트
        assertEquals(2, scores.size());

        //csv 파일의 B유저 점수와 찾은 점수가 일치여부 테스트
        List<Score> score = service.getScoreByStudentName("B");
        Score score1 = new Score(2, 80);
        assertEquals(score1, score.get(0));
    }

    @Test
    void getScoreByStudentSeq() {
        Score score = service.getScoreByStudentSeq(1);
        Score score1 = new Score(1, 30);
        //검색 결과가 데이터와 일치하는지 테스트
        assertEquals(score1, score);
    }
}