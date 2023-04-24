package com.nhnacademy.board.springmvcboard.controller.user;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.init.RequestMapping;
import com.nhnacademy.notice_board.item.post.Post;
import com.nhnacademy.notice_board.repository.post.Page;
import com.nhnacademy.notice_board.repository.post.PostRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RequestMapping("/post-list.do")
public class PostListFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        int pageNum;
        String pageNumOb = req.getParameter("pageNum");
        if (Objects.isNull(pageNumOb)) {
            pageNum = 1;
        } else {
            pageNum = Integer.parseInt(pageNumOb);
        }
        Page<Post> pagedPosts = postRepository.getPagedPosts(pageNum, 10);
        req.setAttribute("pagePosts",pagedPosts);
        return "/user/post-list.jsp";
    }
}
