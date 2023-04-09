package com.nhnacademy.student.servlet;

import com.nhnacademy.student.item.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "studentDeleteServlet", urlPatterns = "/student/delete")
public class StudentDeleteServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //todo init studentRepository
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
        log.error("StudentDeleteServlet init 실행");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo get parameter  : id , id가 존재하지 않을경우 throw new RuntimeException("...")
        String id = req.getParameter("id");
        if(id == null){
            throw new RuntimeException("잘못된 /student/update 접근 [id 가 null 입니다.]");
        } else if (!studentRepository.existById(id)) {
            throw new RuntimeException("잘못된 /student/update 접근 [id 가 존재하지 않습니다.]");
        } else {
            studentRepository.deleteById(id);
            //todo /student/list <-- redirect
            resp.sendRedirect("/student/list");
        }
    }
}