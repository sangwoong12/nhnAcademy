package com.nhnacademy.springmvcstudent.thymeleaf;

import com.nhnacademy.springmvcstudent.item.Gender;

public class TagUtils {
    public String gender(Gender gender) {
        if (gender.name().equals("M")) {
            return "male";
        } else if (gender.name().equals("F")) {
            return "female";
        } else {
            return "noGender";
        }
    }
}
