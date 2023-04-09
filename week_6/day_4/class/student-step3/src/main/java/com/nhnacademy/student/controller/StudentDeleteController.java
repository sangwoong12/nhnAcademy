package com.nhnacademy.student.controller;

import com.nhnacademy.student.init.RequestMapping;
import com.nhnacademy.student.item.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/student/delete.do", method = RequestMapping.Method.POST)
public class StudentDeleteController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        if(id == null){
            throw new RuntimeException("잘못된 /student/update 접근 [id 가 null 입니다.]");
        }
        if (!studentRepository.existById(id)) {
            throw new RuntimeException("잘못된 /student/update 접근 [id 가 존재하지 않습니다.]");
        }
        studentRepository.deleteById(id);

        return "redirect:/student/list.do";
    }
}
