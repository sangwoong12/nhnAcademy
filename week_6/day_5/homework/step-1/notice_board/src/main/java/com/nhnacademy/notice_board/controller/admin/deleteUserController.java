package com.nhnacademy.notice_board.controller.admin;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.exception.NotFoundIdException;
import com.nhnacademy.notice_board.init.RequestMapping;
import com.nhnacademy.notice_board.repository.user.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/delete-user.do")
public class deleteUserController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        String id = req.getParameter("id");

        if (!userRepository.existById(id)) {
            throw new NotFoundIdException();
        } else {
            userRepository.remove(id);
            return "redirect:/user-list.do";
        }
    }
}
