package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.exception.CsvParsingException;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CsvScores implements Scores {
    private final List<Score> scoreList = new ArrayList<>();

    private final String path = Objects.requireNonNull(CsvScores.class.getResource("/")).getPath();

    @Override
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path + "/data/score.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                int seq = Integer.parseInt(tokens[0]);
                int score = Integer.parseInt(tokens[1]);
                scoreList.add(new Score(seq, score));
            }
        } catch (IOException e) {
            throw new CsvParsingException();
        }
    }

    @Override
    public List<Score> findAll() {
        return scoreList;
    }
}
