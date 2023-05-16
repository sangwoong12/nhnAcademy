package com.nhnacademy.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home/admin")
    public String adminHome() {
        return "admin";
    }

    @GetMapping("/home/member")
    public String memberHome() {
        return "member";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
