package com.nhnacademy.board.springmvcboard.login;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.init.RequestMapping;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RequestMapping(value = "/login.do", method = RequestMapping.Method.POST)
public class loginController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext sc = req.getServletContext();

        UserRepository userRepository = (UserRepository) sc.getAttribute("userRepository");
        String id = req.getParameter("id");
        String pwd = req.getParameter("password");

        log.info("login : pwd {} : {}",pwd,userRepository.getUser(id).getPassword());

        if(!userRepository.existById(id)) {
            throw new RuntimeException("존재하지 않는 아이디 입니다.");
        }

        if(!userRepository.getUser(id).getPassword().equals(pwd)) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        Integer count = (Integer) sc.getAttribute("count");
        count++;

        sc.setAttribute("count",count);
        HttpSession session = req.getSession();
        session.setAttribute("id",id);
        return "redirect:/login.do";
    }
}
