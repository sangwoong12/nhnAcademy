package com.nhnacademy.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index(){
        return "index";
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/")
//    public String postIndex(){
//        return "";
//    }

}
