package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.exception.CsvParsingException;
import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SuppressWarnings(value = "-Xlint:unchecked")
class StudentsTest {

    private final static Students students = CsvStudents.getInstance();
    private final static Scores scores = CsvScores.getInstance();


    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(CsvStudents.class, "studentList", new ArrayList<>());
        ReflectionTestUtils.setField(students, "path", CsvScores.class.getResource("/").getPath());
        ReflectionTestUtils.setField(scores, "path", CsvScores.class.getResource("/").getPath());
    }

    @Test
    void load() {
        //예외 없이 잘 처리되었는지 확인.
        Assertions.assertDoesNotThrow(students::load);
        //성공적으로 주입이후 데이터가 잘 들어갔는지 추가 확인.
        List<Student> studentList = (List<Student>) ReflectionTestUtils.getField(students, "studentList");
        assert studentList != null;
        Assertions.assertEquals(4, studentList.size());
    }

    @Test
    void loadExceptionTest() {
        ReflectionTestUtils.setField(CsvStudents.class, "studentList", new ArrayList<>());
        ReflectionTestUtils.setField(students, "path", "");
        Assertions.assertThrows(CsvParsingException.class, students::load);
    }

    @Test
    void findAll() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "A"));
        studentList.add(new Student(2, "B"));
        studentList.add(new Student(3, "A"));
        studentList.add(new Student(4, "D"));

        students.load();

        List<Student> studentList2 = new ArrayList<>(students.findAll());
        //값 확인
        for (int i = 0; i < studentList.size(); i++) {
            Assertions.assertEquals(studentList.get(i).getSeq(), studentList2.get(i).getSeq());
            Assertions.assertEquals(studentList.get(i).getName(), studentList2.get(i).getName());
        }
    }

    @Test
    void merge() {

        scores.load();
        students.load();

        students.merge(scores.findAll());

        List<Student> studentList = new ArrayList<>(students.findAll());
        //3번 까지는 score 가 존재 Not NULL 이 발생해야함.
        for (int i = 0; i < 3; i++) {
            assertNotNull(studentList.get(i).getScore());
        }
        //4번은 score 가 존재하지 않아 NULL 이 발생해야함.
        assertNull(studentList.get(3).getScore());
        Assertions.assertEquals(4, students.findAll().size());

        Student student1 = new Student(1, "A");
        student1.setScore(new Score(1, 30));
        Student student2 = new Student(2, "B");
        student2.setScore(new Score(2, 80));
        Student student3 = new Student(3, "A");
        student3.setScore(new Score(3, 70));
        Student student4 = new Student(4, "D");

        Assertions.assertArrayEquals(
                students.findAll().toArray(),
                new Object[]{student1, student2, student3, student4});

    }
}