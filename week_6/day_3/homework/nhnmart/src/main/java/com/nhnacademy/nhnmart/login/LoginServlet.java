package com.nhnacademy.nhnmart.login;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(
        name = "loginServlet",
        urlPatterns = "/login",
        initParams = {
                @WebInitParam(name="id",value = "admin"),
                @WebInitParam(name="pwd", value = "1234")
        }
)
@Slf4j
public class LoginServlet extends HttpServlet {
    private String initParamId;
    private String initParamPwd;

    @Override
    public void init(ServletConfig config) throws ServletException {

        initParamId = config.getInitParameter("id");
        initParamPwd = config.getInitParameter("pwd");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(Objects.isNull(session) || Objects.isNull(session.getAttribute("id")) ){
            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.forward(req,resp);
        }else{
            RequestDispatcher rd = req.getRequestDispatcher("/loginHome.jsp");
            rd.forward(req,resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

        if(initParamId.equals(id) && initParamPwd.equals(pwd)){
            //session 있으면 가져오고 없으면 생성
            System.out.println("Awfsegrdhftjgykuhij");
            HttpSession session = req.getSession();
            session.setAttribute("id",id);
            resp.sendRedirect("/login");
        }else{
            log.error("아이디/패스워드가 일치하지 않습니다.");
//            resp.sendRedirect("/login.html");
            //데이터를 넣지 않을 경우 412error 발생
            RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.forward(req,resp);
            log.error("id :{}",id);
        }
    }

}
