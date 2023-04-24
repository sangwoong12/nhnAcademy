package com.nhnacademy.springmvcstudent.item;

import java.time.LocalDateTime;

public class Student {
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
    public Student(){}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
}
