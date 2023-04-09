package com.nhnacademy.nhnmart.login;

import com.nhnacademy.nhnmart.controller.Command;
import com.nhnacademy.nhnmart.init.RequestMapping;
import com.nhnacademy.nhnmart.item.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RequestMapping(value = "/login.do", method = RequestMapping.Method.POST)
public class loginController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");

        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        log.info(id+":"+pwd);
        if(!userRepository.exist(id)) {
            throw new RuntimeException("존재하지 않는 아이디 입니다.");
        }
        if(!userRepository.getuser(id).getId().equals(pwd)) {
            throw new RuntimeException("존재하지 않는 비밀번호 입니다.");
        }
        HttpSession session = req.getSession();
        userRepository.getuser(id).setMoney(20000);
        session.setAttribute("id",id);
        return "redirect:/login.do";
    }
}
