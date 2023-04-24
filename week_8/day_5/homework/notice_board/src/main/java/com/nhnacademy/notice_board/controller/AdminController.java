package com.nhnacademy.notice_board.controller;

import com.nhnacademy.notice_board.domain.UserRequest;
import com.nhnacademy.notice_board.item.Page;
import com.nhnacademy.notice_board.item.user.User;
import com.nhnacademy.notice_board.item.user.UserImpl;
import com.nhnacademy.notice_board.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminController {
    UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String home() {
        return "admin/home";
    }

    @GetMapping("/user-list")
    public String getUserListByPageNum(@RequestParam(value = "pageNum", required = false) String pageNum, Model model) {
        int pageNumber = 1;
        if (Objects.nonNull(pageNum)) {
            pageNumber = Integer.parseInt(pageNum);
        }
        Page<User> pagedUsers = userService.getPagedUsers(pageNumber, 10);
        model.addAttribute("pagedUsers", pagedUsers);
        return "admin/user-list";
    }

    @GetMapping("/edit-user/{userId}")
    public String getEditForm(@PathVariable("userId") String userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "admin/user-edit";
    }
    @GetMapping("/view-user/{userId}")
    public String getViewUserById(@PathVariable("userId") String userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "admin/user-view";
    }

    @GetMapping("/add-user")
    public String getAddUserForm(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "admin/user-add";
    }

    @GetMapping("/delete-user/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/user-list";
    }
}
