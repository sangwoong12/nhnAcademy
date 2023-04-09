package com.nhnacademy.student.controller;

import com.nhnacademy.student.item.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class StudentViewController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");
        if(id == null){
            throw new RuntimeException("잘못된 /student/view 접근 [id 가 null 입니다.]");
        }
        log.info("id 가 들어왔습니다.");
        if (!studentRepository.existById(id)) {
            throw new RuntimeException("잘못된 /student/view 접근 [id 가 존재하지 않습니다.]");
        }
        req.setAttribute("student",studentRepository.getStudentById(id));
        return "/student/view.jsp";
    }
}
