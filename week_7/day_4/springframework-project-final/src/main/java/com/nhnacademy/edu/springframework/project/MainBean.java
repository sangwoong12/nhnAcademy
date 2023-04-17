package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.GradeQueryService;
import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class MainBean {
    private final StudentService studentService;
    private final GradeQueryService gradeQueryService;

    @Autowired
    public MainBean(DataLoadService dataLoadService, StudentService studentService, GradeQueryService gradeQueryService) {
        dataLoadService.loadAndMerge();
        this.studentService = studentService;
        this.gradeQueryService = gradeQueryService;
    }

    public void printPassedStudent() {
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);
    }

    public void printStudentOrderByScore() {
        Collection<Student> studentsOrderByScore = studentService.getStudentsOrderByScore();
        System.out.println(studentsOrderByScore);
    }

    public void printScoreByStudentName(String name) {
        List<Score> scoreByStudentName = gradeQueryService.getScoreByStudentName(name);
        System.out.println(scoreByStudentName);
    }

    public void printScoreByStudentSeq(int req) {
        Score scoreByStudentSeq = gradeQueryService.getScoreByStudentSeq(req);
        System.out.println(scoreByStudentSeq);
    }
}
