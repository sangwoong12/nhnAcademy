package com.nhnacademy.flight.item;

public class Passenger {
    private int id;
    private int age;
    private int grade;
    private String name;

    public Passenger(int id, int age, int grade, String name) {
        this.id = id;
        this.age = age;
        this.grade = grade;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }
}
