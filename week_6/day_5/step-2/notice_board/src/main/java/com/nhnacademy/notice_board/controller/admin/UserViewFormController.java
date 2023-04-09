package com.nhnacademy.notice_board.controller.admin;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.init.RequestMapping;
import com.nhnacademy.notice_board.item.user.User;
import com.nhnacademy.notice_board.repository.user.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/user-view.do")
public class UserViewFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");

        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        User user = userRepository.getUser(id);
        req.setAttribute("user",user);
        return "/admin/user-view.jsp";
    }
}
