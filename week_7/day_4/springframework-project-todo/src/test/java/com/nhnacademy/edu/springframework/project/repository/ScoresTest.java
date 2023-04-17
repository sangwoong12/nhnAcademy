package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.exception.CsvParsingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Objects;


class ScoresTest {

    private final static Scores scores = CsvScores.getInstance();

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(CsvScores.class, "scoreList", new ArrayList<>());
        ReflectionTestUtils.setField(scores, "path", Objects.requireNonNull(CsvScores.class.getResource("/")).getPath());
    }

    @Test
    void load() {
        Assertions.assertDoesNotThrow(scores::load);
    }

    @Test
    void loadExceptionTest() {
        scores.load();
        ReflectionTestUtils.setField(scores, "path", "");
        Assertions.assertThrows(CsvParsingException.class, scores::load);
    }

    @Test
    void findAll() {
        scores.load();
        Score score1 = new Score(1, 30);
        Score score2 = new Score(2, 80);
        Score score3 = new Score(3, 70);

        //값 확인
        Assertions.assertArrayEquals(new Object[]{score1, score2, score3}, scores.findAll().toArray());
    }
}