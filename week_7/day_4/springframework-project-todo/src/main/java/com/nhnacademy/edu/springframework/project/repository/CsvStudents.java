package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.exception.CsvParsingException;
import com.nhnacademy.edu.springframework.project.service.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class CsvStudents implements Students {
    private static CsvStudents csvStudents;

    private static List<Student> studentList = new ArrayList<>();

    private static String path = Objects.requireNonNull(CsvScores.class.getResource("/")).getPath();

    private CsvStudents() {
    }

    /**
     * TODO 3 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Students getInstance() {
        if (Objects.isNull(csvStudents)) {
            synchronized (CsvScores.class) {
                if (Objects.isNull(csvStudents)) {
                    csvStudents = new CsvStudents();
                }
            }
        }
        return csvStudents;
    }


    // TODO 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
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

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     *
     * @param scores
     */
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
