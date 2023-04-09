package com.nhnacademy.nhnmart.login;

import com.nhnacademy.nhnmart.controller.Command;
import com.nhnacademy.nhnmart.init.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@RequestMapping(value = "/logout.do")
public class logoutController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //session 있으면 가져오고 없으면 null
        HttpSession session = req.getSession(false);

        if(Objects.nonNull(session)) {
            session.invalidate();
        }
        return "/login.jsp";
    }
}
