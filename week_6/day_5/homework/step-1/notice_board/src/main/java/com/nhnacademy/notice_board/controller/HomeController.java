package com.nhnacademy.notice_board.controller;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.init.RequestMapping;
import com.nhnacademy.notice_board.item.user.Admin;
import com.nhnacademy.notice_board.item.user.User;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping("/home.do")
public class HomeController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");

        String id  = (String) req.getSession().getAttribute("id");

        User user = userRepository.getUser(id);

        if (user instanceof Admin) {
            log.info("admin 이 입장하였습니다.");
            return "/admin/admin-home.jsp";
        } else {
            log.info("user가 입장하였습니다.");
            return "/user/user-home.jsp";
        }
    }
}
