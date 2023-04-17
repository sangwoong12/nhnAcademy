package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.stereotype.Service;

@Service
public class CsvDataLoadService implements DataLoadService {
    Students students;
    Scores scores;

    public CsvDataLoadService(Students students, Scores scores) {
        this.students = students;
        this.scores = scores;
    }

    @Override
    public void loadAndMerge() {
        scores.load();
        students.load();
        students.merge(scores.findAll());
    }
}
