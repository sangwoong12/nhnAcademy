package com.nhnacademy.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import java.util.Objects;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(@CookieValue(value = "SESSION", required = false) Cookie cookie) {
        if (Objects.nonNull(cookie)) {
            return "redirect:/home";
        }
        return "login";
    }
}
