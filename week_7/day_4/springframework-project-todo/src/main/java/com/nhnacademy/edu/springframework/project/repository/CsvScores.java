package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.exception.CsvParsingException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CsvScores implements Scores {
    private static CsvScores csvScores;
    private static List<Score> scoreList = new ArrayList<>();

    private static String path = Objects.requireNonNull(CsvScores.class.getResource("/")).getPath();

    private CsvScores() {
    }

    /**
     * TODO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Scores getInstance() {
        if (Objects.isNull(csvScores)) {
            synchronized (CsvScores.class) {
                if (Objects.isNull(csvScores)) {
                    csvScores = new CsvScores();
                }
            }
        }
        return csvScores;
    }

    //TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
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
