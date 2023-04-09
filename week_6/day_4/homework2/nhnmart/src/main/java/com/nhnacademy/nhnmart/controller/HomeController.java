package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.init.RequestMapping;
import com.nhnacademy.nhnmart.item.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/home.do")
public class HomeController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        String id = (String) req.getSession().getAttribute("id");
        int money = userRepository.getuser(id).getMoney();
        req.setAttribute("money",money);
        return "index.jsp";
    }
}
