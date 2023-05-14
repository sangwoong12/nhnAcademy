package com.nhnacademy.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
    @GetMapping(value = {"/",""})
    public String home(){
        return "user/home";
    }
}
