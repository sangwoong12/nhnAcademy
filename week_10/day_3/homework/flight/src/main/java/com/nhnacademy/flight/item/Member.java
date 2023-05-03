package com.nhnacademy.flight.item;

public class Member {
    private int id;
    private String name;
    private String city;

    public Member(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}
