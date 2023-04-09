package com.nhnacademy.student.servlet;


import com.nhnacademy.student.item.Gender;
import com.nhnacademy.student.item.Student;
import com.nhnacademy.student.item.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")
@Slf4j
public class StudentRegisterServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
        log.error("StudentRegisterServlet init 실행");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo  /student/register.jsp forward 합니다.
        req.setAttribute("view","/student/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo null check
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Gender gender = req.getParameter("gender") != null ? Gender.valueOf(req.getParameter("gender")) : null;
        Integer age = req.getParameter("age") != null ? Integer.parseInt(req.getParameter("age")) : null;

        if(id == null || age == null || gender == null || name == null){
            throw new RuntimeException("잘못된 /student/list 접근 입니다.");
        }
        else {
            Student student = Student.builder()
                    .id(id)
                    .age(age)
                    .gender(gender)
                    .name(name)
                    .createAt(LocalDateTime.now())
                    .build();
            //todo save 구현
            studentRepository.save(student);
            //todo redirect /student/view?id=student1
            req.setAttribute("view","redirect:/student/view.do?id="+student.getId());
        }
    }
}