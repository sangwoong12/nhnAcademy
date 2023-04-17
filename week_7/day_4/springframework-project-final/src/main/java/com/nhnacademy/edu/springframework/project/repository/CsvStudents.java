package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.exception.CsvParsingException;
import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Repository
public class CsvStudents implements Students {
    private final List<Student> studentList = new ArrayList<>();

    private final String path = Objects.requireNonNull(CsvScores.class.getResource("/")).getPath();

    @Override
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path + "/data/student.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                int seq = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                studentList.add(new Student(seq, name));
            }
        } catch (IOException e) {
            throw new CsvParsingException();
        }
    }

    @Override
    public Collection<Student> findAll() {
        return studentList;
    }

    @Override
    public void merge(Collection<Score> scores) {
        Map<Integer, Student> studentMap = studentList.stream().collect(Collectors.toMap(Student::getSeq, Function.identity()));
        scores.forEach(score -> {
            Student student = studentMap.get(score.getStudentSeq());
            if (student != null) {
                student.setScore(score);
            }
        });
    }
}
