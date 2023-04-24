package com.nhnacademy.board.springmvcboard.controller.admin;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.init.RequestMapping;
import com.nhnacademy.notice_board.item.user.UserImpl;
import com.nhnacademy.notice_board.item.user.User;
import com.nhnacademy.notice_board.repository.user.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/edit-user-info.do", method = RequestMapping.Method.POST)
public class EditUserController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String profile = req.getParameter("profile");
        String auth = req.getParameter("auth");
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        User user = new UserImpl(id,name,password,profile, auth);

        userRepository.modify(user);

        return "redirect:/user-list.do";
    }
}
