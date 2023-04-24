package com.nhnacademy.notice_board.login;


import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.init.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@RequestMapping(value = "/login.do",method = RequestMapping.Method.GET)
public class loginFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if(Objects.isNull(session) || Objects.isNull(session.getAttribute("id")) ){
            return "/login/loginForm.jsp";
        }else{
            return "/login/loginHome.jsp";
        }
    }
}
