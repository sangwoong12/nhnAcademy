package com.nhnacademy.board.controller;

import com.nhnacademy.board.domain.EditPostRequest;
import com.nhnacademy.board.domain.PostRequest;
import com.nhnacademy.board.entity.Post;
import com.nhnacademy.board.entity.User;
import com.nhnacademy.board.exception.PermissionException;
import com.nhnacademy.board.service.post.JpaPostService;
import com.nhnacademy.board.service.user.JpaUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class PostController {
    JpaPostService postService;
    JpaUserService userService;

    public PostController(JpaPostService postService, JpaUserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public String home() {
        return "user/home";
    }

    @GetMapping("/post-list")
    public String getPostListByPageNum(Pageable pageable, Model model) {
        int pageNumber = 1;
//        if (Objects.nonNull(pageNum)) {
//            pageNumber = Integer.parseInt(pageNum);
//        }
        Page<Post> pagedPosts = postService.getPagedPosts(pageable);
        model.addAttribute("pagedPosts", pagedPosts);
        return "user/post-list";
    }

    @GetMapping("/view-post")
    public String getPost(@RequestParam("postId") long postId, Model model, HttpServletRequest req, HttpServletResponse resp) {
        Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        //filter 에서 본적이 없는 게시물이면 count 를 보냄.
        if (Objects.nonNull(req.getAttribute("count"))) {
            postService.increaseCount(post);
            //view count 처리를 위한 쿠키
            Cookie cookie = new Cookie(String.valueOf(postId), String.valueOf(postId));
            cookie.setMaxAge(60 * 60 * 24);
            cookie.setHttpOnly(true); // HttpOnly 플래그 사용
            cookie.setSecure(true);

            resp.addCookie(cookie);
        }
        return "user/post-view";
    }

    @GetMapping("/delete-post")
    public String deletePost(@RequestParam("postId") long postId) {
        postService.deletePost(postId);
        return "redirect:/user/post-list";
    }

    //본인 여부가 필요한 메서드
    @GetMapping("/add-post")
    public String getAddPostForm(Model model) {
        model.addAttribute("postRequest", new PostRequest());
        return "user/post-add-form";
    }

    @PostMapping("/add-post")
    public String addPost(@ModelAttribute PostRequest postRequest, HttpServletRequest req) {
        String userId = (String) req.getSession().getAttribute("id");
        if (Objects.isNull(userId)) {
            throw new PermissionException();
        }
        User user = userService.getUser(userId);
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setWriteTime(LocalDateTime.now());
        post.setViewCount(0);
        post.setId(postRequest.getId());
        post.setUser(user);

        postService.addPost(post);
        return "redirect:/user/post-list";
    }

    @GetMapping("/edit-post")
    public String getEditPostForm(@RequestParam("postId") long postId, Model model, HttpServletRequest req) {
        String userId = (String) req.getSession().getAttribute("id");
        Post post = postService.getPost(postId);
        if (!post.getUser().getId().equals(userId)) {
            throw new PermissionException();
        }
        PostRequest postRequest = new PostRequest(postId, post.getTitle(), post.getContent());
        model.addAttribute("postRequest", postRequest);
        return "user/post-edit-form";
    }

    @PostMapping("/edit-post")
    public String editPost(EditPostRequest editPostRequest, HttpServletRequest req) {
        String userId = (String) req.getSession().getAttribute("id");
        Post post = postService.getPost(editPostRequest.getId());
        if (!post.getUser().getId().equals(userId)) {
            throw new PermissionException();
        }
//        postService.modifyPost();
        return "redirect:/user/view-post?postId=" + post.getId();
    }

    @GetMapping("/view-user")
    public String viewUser(@RequestParam("userId") String userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "user/view-user";
    }
}
