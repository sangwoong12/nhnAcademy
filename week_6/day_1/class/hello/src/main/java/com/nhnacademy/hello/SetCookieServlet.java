package com.nhnacademy.hello;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class SetCookieServlet extends HelloServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        String locale = req.getParameter("locale");
        if (Objects.isNull(locale) || locale.isEmpty()) {
            locale = "ko";
        }
        Cookie cookie = new Cookie("locale",locale);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        resp.addCookie(cookie);

        try (PrintWriter out = resp.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<script>alert('쿠키 생성완료')</script>");
            out.println("</html>");
            out.println("</head>");

        }
    }
}
