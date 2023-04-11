package com.nhnacademy.notice_board.controller.admin;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.exception.NotFoundIdException;
import com.nhnacademy.notice_board.init.RequestMapping;
import com.nhnacademy.notice_board.item.user.User;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping("/edit-user-info.do")
public class EditUserInfoFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        String id = req.getParameter("id");

        if (!userRepository.existById(id)) {
            throw new NotFoundIdException();
        } else {
            User user = userRepository.getUser(id);
            req.setAttribute("user",user);
            return "/admin/edit-user-info.jsp";
        }
    }
}
