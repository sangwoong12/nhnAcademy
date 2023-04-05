package com.nhnacademy.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Objects;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(Objects.isNull(session)){
            resp.sendRedirect("/login.html");
        }else{
            session.invalidate();
        }

        resp.sendRedirect("/login.html");
    }
}
