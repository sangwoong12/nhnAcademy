package com.nhnacademy.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentRegisterFromController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/student/register.jsp";
    }
}
