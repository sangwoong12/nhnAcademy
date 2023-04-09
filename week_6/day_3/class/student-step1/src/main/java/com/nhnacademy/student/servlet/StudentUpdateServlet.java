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

@Slf4j
@WebServlet(name = "studentUpdateServlet", urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {
    private StudentRepository studentRepository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        //todo init studentRepository
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
        log.error("StudentUpdateServlet init 실행");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id == null) {
            throw new RuntimeException("잘못된 /student/update 접근 [id 가 null 입니다.]");
        } else if (!studentRepository.existById(id)){
            throw new RuntimeException("잘못된 /student/update 접근 [id 가 존재하지 않습니다.]");
        } else {
            // todo 학생조회
            Student student = studentRepository.getStudentById(id);
            req.setAttribute("student",student);

            //todo forward : /student/register.jsp
            req.getRequestDispatcher("/student/register.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo null check
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Gender gender = req.getParameter("gender") != null ? Gender.valueOf(req.getParameter("gender")) : null;
        Integer age = req.getParameter("age") != null ? Integer.parseInt(req.getParameter("age")) : null;

        if(id == null || age == null || gender == null || name == null){
            throw new RuntimeException("잘못된 /student/update 접근 [필요한 parameter 가 null 입니다.]");
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
            studentRepository.update(student);
            //todo /student/view?id=student1 <-- redirect
            resp.sendRedirect("/student/view?id="+student.getId());
        }
    }
}