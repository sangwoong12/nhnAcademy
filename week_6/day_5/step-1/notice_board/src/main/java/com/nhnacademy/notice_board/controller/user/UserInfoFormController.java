package com.nhnacademy.notice_board.controller.user;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.init.RequestMapping;
import com.nhnacademy.notice_board.repository.user.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequestMapping("/user-info.do")
public class UserInfoFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");

        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        req.setAttribute("user",userRepository.getUser(id));
        return "/user/user-info.jsp";
    }
}
