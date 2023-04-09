package com.nhnacademy.student.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Student {
    private String id;
    private String name;
    private Gender gender;
    private int age;
    private LocalDateTime createAt;
}
