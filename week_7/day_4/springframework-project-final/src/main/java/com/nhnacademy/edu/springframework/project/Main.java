package com.nhnacademy.edu.springframework.project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.project.config")){
            MainBean mainBean = context.getBean("mainBean", MainBean.class);
            mainBean.printPassedStudent();
            mainBean.printStudentOrderByScore();
            mainBean.printScoreByStudentName("A");
            mainBean.printScoreByStudentSeq(1);
        }
    }
}
