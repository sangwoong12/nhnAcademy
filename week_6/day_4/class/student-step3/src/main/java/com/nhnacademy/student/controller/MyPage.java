package com.nhnacademy.student.controller;

import com.nhnacademy.student.init.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/mypage", method = RequestMapping.Method.GET)
public class MyPage implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "mypage.jsp";
    }
}
