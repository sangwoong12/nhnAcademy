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
@WebServlet(name = "studentViewServlet", urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
        log.error("StudentViewServlet init 실행");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo id null check
        String id = req.getParameter("id");
        if(id == null){
            throw new RuntimeException("잘못된 /student/view 접근 [id 가 null 입니다.]");
        }
        else if(!studentRepository.existById(id)){
            throw new RuntimeException("잘못된 /student/view 접근 [id 가 존재하지 않습니다.]");
        }
        else {
            //todo student 조회
            req.setAttribute("student",studentRepository.getStudentById(id));
            //todo /student/view.jsp <-- forward
            req.setAttribute("view","/student/view.jsp");
        }
    }
}
