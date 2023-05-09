package com.nhnacademy.board.controller;

import com.nhnacademy.board.domain.UserRequest;
import com.nhnacademy.board.entity.User;
import com.nhnacademy.board.service.user.JpaUserService;
import com.nhnacademy.board.utils.UserUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@Controller
public class ProfileController {

    JpaUserService userService;

    private static final String JPEG = ".jpeg";
    private static final String IMAGE = "/image";
    private static final String UPLOAD_DIR = Objects.requireNonNull(ProfileController.class.getResource("/")).getPath();

    public ProfileController(JpaUserService userService) {
        this.userService = userService;
    }


    @PostMapping("/profile")
    public String addProfile(
            @ModelAttribute UserRequest userRequest) throws IOException {

        User user = UserUtils.createByUserRequest(userRequest);

        if (Objects.nonNull(userRequest.getImage())) {
            if (userRequest.getImage().getOriginalFilename() != null) {
                userRequest.getImage().transferTo(Paths.get(UPLOAD_DIR + IMAGE + File.separator + user.getId() + JPEG));
            }
        }
        userService.addUser(user);
        return "redirect:/admin/view-user/" + user.getId();
    }

    @GetMapping("/profile")
    public ResponseEntity<byte[]> getProfile(@RequestParam("id") String id) throws IOException {
        User user = userService.getUser(id);
        // id 값에 해당하는 이미지 파일을 읽어옵니다.
        String fileName = user.getProfileFileName() == null ? "no-image.png" : user.getProfileFileName();

        File imageFile = new File(UPLOAD_DIR + IMAGE + File.separator + fileName);
        byte[] imageBytes = Files.readAllBytes(imageFile.toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @PostMapping("/edit-user")
    public String editUser(@ModelAttribute UserRequest userRequest) throws IOException {
        User user = UserUtils.createByUserRequest(userRequest);

        if (Objects.nonNull(userRequest.getImage())) {
            if (userRequest.getImage().getOriginalFilename() != null) {
                userRequest.getImage().transferTo(Paths.get(UPLOAD_DIR + IMAGE + File.separator + user.getId() + JPEG));
            }
        }
        userService.modifyUser(user);
        return "redirect:/admin/view-user/" + user.getId();
    }
}

