package com.nhnacademy.hello;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Slf4j
public class LoginServlet extends HttpServlet {
    private String userId;
    private String userPassword;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userId = config.getInitParameter("id");
        userPassword = config.getInitParameter("pwd");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if(Objects.isNull(session)){
            resp.sendRedirect("/login.html");
        }else{
            try(PrintWriter out = resp.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset='utf-8'>");
                out.println("</head>");
                out.println("<body>");
                out.println("login success : id =" + session.getAttribute("id") + "<br/>");
                out.println("session id : " + session.getId());
                out.println("<form method='post' action='/logout'>");
                out.println("<button type='submit'>logout</button>");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
            }catch (IOException e){
                log.error("login : {}",e.getMessage());
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("pwd");

        log.info(id + password);
        log.info(userId + userPassword);
        if(id.equals(userId) && password.equals(userPassword)){
            HttpSession session = req.getSession(true);
            session.setAttribute("id",id);
            resp.sendRedirect("/login");
        }else{
            log.error("아이디와 패스워드가 일치하지 않습니다.");
            resp.sendRedirect("/login.html");
        }
    }
}
