package com.nhnacademy.student.controller;

import com.nhnacademy.student.init.RequestMapping;
import com.nhnacademy.student.item.Gender;
import com.nhnacademy.student.item.Student;
import com.nhnacademy.student.item.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RequestMapping(value = "/student/register.do", method = RequestMapping.Method.POST)
public class StudentRegisterController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Gender gender = req.getParameter("gender") != null ? Gender.valueOf(req.getParameter("gender")) : null;
        Integer age = req.getParameter("age") != null ? Integer.parseInt(req.getParameter("age")) : null;

        if(id == null || age == null || gender == null || name == null){
            throw new RuntimeException("잘못된 /student/list 접근 입니다.");
        }
        Student student = Student.builder()
                .id(id)
                .age(age)
                .gender(gender)
                .name(name)
                .createAt(LocalDateTime.now())
                .build();
        studentRepository.save(student);

        return "redirect:/student/view.do?id="+student.getId();
    }
}
