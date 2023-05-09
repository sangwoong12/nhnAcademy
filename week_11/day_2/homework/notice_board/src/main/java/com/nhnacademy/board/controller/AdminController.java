package com.nhnacademy.board.controller;

import com.nhnacademy.board.domain.UserRequest;
import com.nhnacademy.board.entity.User;
import com.nhnacademy.board.service.user.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminController {

    JpaUserService userService;

    @Autowired
    public AdminController(JpaUserService userService) {
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
