package com.nhnacademy.student.controller;

import com.nhnacademy.student.item.Student;
import com.nhnacademy.student.item.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentUpdateFromController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        if(id == null) {
            throw new RuntimeException("잘못된 /student/update 접근 [id 가 null 입니다.]");
        } else if (!studentRepository.existById(id)){
            throw new RuntimeException("잘못된 /student/update 접근 [id 가 존재하지 않습니다.]");
        }
        Student student = studentRepository.getStudentById(id);
        req.setAttribute("student",student);

        return "/student/register.jsp";
    }
}
