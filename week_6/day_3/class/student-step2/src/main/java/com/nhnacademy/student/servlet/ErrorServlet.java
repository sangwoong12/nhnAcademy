package com.nhnacademy.student.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.RequestDispatcher.*;

@Slf4j
@WebServlet(name = "errorServlet", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));

        //todo exception_type
        req.setAttribute("exception_type",req.getAttribute(ERROR_EXCEPTION_TYPE));
        //todo message
        req.setAttribute("message",req.getAttribute(ERROR_MESSAGE));
        //todo exception
        req.setAttribute("exception",req.getAttribute(ERROR_EXCEPTION));
        //todo request_uri
        req.setAttribute("request_uri",req.getAttribute(ERROR_REQUEST_URI));

        //todo /error.jsp forward 처리
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("error.jsp");
        requestDispatcher.forward(req,resp);
    }

}