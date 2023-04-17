package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GradeQueryServiceTest {
    static Students students = new CsvStudents();
    static Scores scores = new CsvScores();
    private final static DataLoadService dataLoadService = new CsvDataLoadService(students, scores);
    private static final GradeQueryService service = new DefaultGradeQueryService(students);

    @BeforeAll
    public static void setUpClass() {
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