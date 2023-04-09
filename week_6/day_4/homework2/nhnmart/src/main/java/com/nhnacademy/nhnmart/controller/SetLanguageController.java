package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.init.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/change-lang.do")
public class SetLanguageController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String locale = req.getParameter("locale");
        Cookie cookie = new Cookie("locale",locale);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        resp.addCookie(cookie);

        return "redirect:/home.do";
    }
}
