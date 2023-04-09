package com.nhnacademy.notice_board.controller.admin;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.init.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/user-add.do", method = RequestMapping.Method.GET)
public class UserAddFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/admin/user-add.jsp";
    }
}
