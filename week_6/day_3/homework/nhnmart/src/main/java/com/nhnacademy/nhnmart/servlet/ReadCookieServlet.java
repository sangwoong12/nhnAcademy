package com.nhnacademy.nhnmart.servlet;


import com.nhnacademy.nhnmart.util.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@WebServlet(name = "readCookieServlet",urlPatterns = "/read-cookie")
public class ReadCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = CookieUtils.getCookie(req,"locale");

        if(Objects.isNull(cookie)){
            resp.sendError(500,"cookie not found");
            return ;
        }

        String locale = cookie.getValue();

        String helloValue = ResourceBundle.getBundle("message", new Locale(locale)).getString("hello");

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        try(PrintWriter out = resp.getWriter()){
            out.println(helloValue);
        }
    }

}
