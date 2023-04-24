package com.nhnacademy.springmvcstudent.controller;

import com.nhnacademy.springmvcstudent.item.LoginRequest;
import com.nhnacademy.springmvcstudent.item.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/login")
@Slf4j
public class loginController {
    private final User user;

    public loginController(User user) {
        this.user = user;
    }

    @ModelAttribute("user")
    public User getUser(@CookieValue(value = "SESSION", required = false) String sessionId, HttpServletRequest req) {
        if (Objects.isNull(req.getSession().getAttribute("user"))) {
            return null;
        }
        return StringUtils.hasText(sessionId) ? user : null;
    }

    @GetMapping(value = {"/", ""})
    public String loginForm(Model model, User user) {
        if (Objects.nonNull(user)) {
            return "redirect:/student/list.do";
        }
        model.addAttribute("loginRequest", new LoginRequest());
        return "login/loginForm";
    }

    @PostMapping(value = {"/", ""})
    public String login(@Valid LoginRequest loginRequest, BindingResult bindingResult, HttpServletResponse resp, HttpServletRequest req
            , RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        if (loginRequest.getUserId().equals("admin") && loginRequest.getUserPassword().equals("1234")) {
            HttpSession session = req.getSession(true);

            Cookie cookie = new Cookie("SESSION", session.getId());
            resp.addCookie(cookie);
            session.setAttribute("user", user);
            return "redirect:/student/list.do";
        }
        redirectAttributes.addFlashAttribute("message", "로그인 실패");
        return "redirect:/login";
    }
}
