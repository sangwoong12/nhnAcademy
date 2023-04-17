package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.service.*;

import java.util.Collection;
import java.util.List;

public class Main {

    //TODO 9 - 성공적으로 실행되어야 합니다.
    public static void main(String[] args) {
        DataLoadService dataLoadService = new CsvDataLoadService();
        StudentService studentService = new DefaultStudentService();
        GradeQueryService gradeQueryService = new DefaultGradeQueryService();
        dataLoadService.loadAndMerge();
        System.out.println("----- getPassedStudents -----");
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);
        System.out.println("----- end -----");
        System.out.println("----- getStudentsOrderByScore -----");
        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);
        System.out.println("----- end -----");
        System.out.println("----- getScoreByStudentName -----");
        List<Score> a = gradeQueryService.getScoreByStudentName("A");
        a.stream().forEach(System.out::println);
        System.out.println("----- end -----");
        System.out.println("----- getScoreByStudentSeq -----");
        Score scoreByStudentSeq = gradeQueryService.getScoreByStudentSeq(10);
        System.out.println(scoreByStudentSeq);
        System.out.println("----- end -----");
    }
}
