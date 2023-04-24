package com.nhnacademy.notice_board.controller;

import com.nhnacademy.notice_board.domain.EditPostRequest;
import com.nhnacademy.notice_board.domain.PostRequest;
import com.nhnacademy.notice_board.exception.PermissionException;
import com.nhnacademy.notice_board.item.Page;
import com.nhnacademy.notice_board.item.post.Post;
import com.nhnacademy.notice_board.item.post.PostImpl;
import com.nhnacademy.notice_board.item.user.User;
import com.nhnacademy.notice_board.service.post.PostService;
import com.nhnacademy.notice_board.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class PostController {
    PostService postService;
    UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public String home() {
        return "user/home";
    }

    @GetMapping("/post-list")
    public String getPostListByPageNum(@RequestParam(value = "pageNum", required = false) String pageNum, Model model) {
        int pageNumber = 1;
        if (Objects.nonNull(pageNum)) {
            pageNumber = Integer.parseInt(pageNum);
        }
        Page<Post> pagedPosts = postService.getPagedPosts(pageNumber, 10);
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
        Post post = PostImpl.createByPostRequest(postRequest, userId);
        postService.addPost(post);
        return "redirect:/user/post-list";
    }

    @GetMapping("/edit-post")
    public String getEditPostForm(@RequestParam("postId") long postId, Model model, HttpServletRequest req) {
        String userId = (String) req.getSession().getAttribute("id");
        Post post = postService.getPost(postId);
        if (!post.getWriterUserId().equals(userId)) {
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
        if (!post.getWriterUserId().equals(userId)) {
            throw new PermissionException();
        }
        postService.modifyPost(editPostRequest.getId(), editPostRequest.getTitle(), editPostRequest.getContent());
        return "redirect:/user/view-post?postId=" + post.getId();
    }

    @GetMapping("/view-user")
    public String viewUser(@RequestParam("userId") String userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "user/view-user";
    }
}
