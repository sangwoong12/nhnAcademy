package com.nhnacademy.student.item;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
public class Student implements Serializable {
    private String id;
    private String name;
    private Gender gender;
    private int age;
    private LocalDateTime createAt;

    public Student(String id, String name, Gender gender, int age, LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createAt = createAt;
    }
    public Student(){

    }
}
