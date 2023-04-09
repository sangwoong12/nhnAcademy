package com.nhnacademy.student.controller;

import com.nhnacademy.student.init.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/student/register.do", method = RequestMapping.Method.GET)
public class StudentRegisterFromController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/student/register.jsp";
    }
}
