package com.nhnacademy.notice_board.controller;

import com.nhnacademy.notice_board.init.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/permission-error.do")
public class PermissionErrorController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        throw new RuntimeException("접근 권한이 없습니다.");
    }
}
