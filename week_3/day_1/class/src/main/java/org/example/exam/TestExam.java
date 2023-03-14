package org.example.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestExam {
    public static void main(String[] args) {
        Professor professor1 = new Professor(1, "LEE-Professor", 50);
        Professor professor2 = new Professor(2, "KIM-Professor", 48);
        Professor professor3 = new Professor(3, "PARK-Professor", 60);
        Student student1 = new Student(1, "LEE-Student", 20);
        Student student2 = new Student(2, "KIM-Student", 42);
        Student student3 = new Student(3, "PARK-Student", 21);


        List<Member> memberList = new ArrayList<>();
        memberList.add(professor1);
        memberList.add(student3);
        memberList.add(professor2);
        memberList.add(professor3);
        memberList.add(student1);
        memberList.add(student2);

        for (Member member : memberList) {
            System.out.println(member);
        }
        System.out.println("------------ Sort MemberList ------------");
        Collections.sort(memberList);
        for (Member member : memberList) {
            System.out.println(member);
        }

    }
}
