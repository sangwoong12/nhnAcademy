package com.nhnacademy.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * 로그인 서블렛 :
 * 회원가입 및 회원에 대한 정보를 담고 있는 DB가 따로 없기 때문에 ID : admin PWD : 1234 로 고정하여 일치하면 로그인되도록 함.
 */
@Slf4j
public class LoginServlet extends HttpServlet {
    private static final String USER_ID = "admin";
    private static final String USER_PASSWORD = "1234";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        if(Objects.isNull(session)){
            resp.sendRedirect("/login.html");
        } else {
            try(PrintWriter out = resp.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<link rel='stylesheet' href='nhnmart.css'>");
                out.println("<meta charset='utf-8'>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div>");
                out.println("세션 아이디 [id : " + session.getId() + "]<br/>");
                out.println("로그인 성공 [ id :" + session.getAttribute("id") + "]<br/>");
                out.println("<a href='/foods'>상품 목록</a>\n");
                out.println("<form method='post' action='/logout'>");
                out.println("<button type='submit'>로그아웃</button>");
                out.println("</form>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            } catch (IOException e) {
                log.error("login : {}",e.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("pwd");

        if(id.equals(USER_ID) && password.equals(USER_PASSWORD)){
            HttpSession session = req.getSession(true);
            session.setAttribute("id",id);
            resp.sendRedirect("/login");
        }else{
            log.error("아이디와 패스워드가 일치하지 않습니다.");
            resp.sendRedirect("/login.html");
        }
    }
}
