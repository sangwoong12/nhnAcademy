package com.nhnacademy.notice_board.controller;

import com.nhnacademy.notice_board.domain.LoginRequest;
import com.nhnacademy.notice_board.item.user.User;
import com.nhnacademy.notice_board.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class LoginController {
    UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User getUser(@CookieValue(value = "SESSION", required = false) String id, HttpServletRequest req) {
        if (Objects.nonNull(id) && Objects.nonNull(req.getSession().getAttribute("user"))) {
            String userId = (String) req.getSession().getAttribute("id");
            return userService.getUser(userId);
        }
        return null;
    }

    @GetMapping
    public String loginForm(Model model, User user) {
        if (Objects.nonNull(user)) {
            return "redirect:/login";
        }
        model.addAttribute("loginRequest", new LoginRequest());
        return "login/loginForm";
    }

    @PostMapping
    public String login(@Valid LoginRequest loginRequest, BindingResult bindingResult, HttpServletResponse resp, HttpServletRequest req
            , RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        if (userService.existUser(loginRequest)) {
            HttpSession session = req.getSession(true);
            //로그인 session 정보
            Cookie cookie = new Cookie("SESSION", session.getId());
            resp.addCookie(cookie);
            cookie.setHttpOnly(true); // HttpOnly 플래그 사용
            cookie.setSecure(true);

            User user = userService.getUser(loginRequest.getUserId());
            session.setAttribute("id", user.getId());
            session.setAttribute("name", user.getName());
            if (user.getId().equals("admin")) {
                return "redirect:/admin";
            }
            return "redirect:/user";
        }
        redirectAttributes.addFlashAttribute("message", "로그인 실패");
        return "redirect:/login";
    }
}
