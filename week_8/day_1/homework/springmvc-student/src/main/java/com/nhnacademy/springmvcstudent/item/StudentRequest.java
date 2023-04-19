package com.nhnacademy.springmvcstudent.item;


import com.nhnacademy.springmvcstudent.validator.EnumPattern;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class StudentRequest {
    @Length(min = 5, max = 20)
    private String id;
    @NotBlank
    @NotEmpty
    private String name;
    @EnumPattern(regexp = "M|F")
    private Gender gender;
    @Min(20)
    @Max(30)
    private int age;

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

}
