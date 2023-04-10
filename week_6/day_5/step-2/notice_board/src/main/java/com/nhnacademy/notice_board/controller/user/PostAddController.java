package com.nhnacademy.notice_board.controller.user;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.controller.exception.NotEnoughParameterException;
import com.nhnacademy.notice_board.init.RequestMapping;
import com.nhnacademy.notice_board.item.post.Post;
import com.nhnacademy.notice_board.item.post.PostImpl;
import com.nhnacademy.notice_board.repository.post.PostRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RequestMapping(value = "/post-add.do" , method = RequestMapping.Method.POST)
public class PostAddController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        String id = (String) req.getSession().getAttribute("id");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if(title == null || content == null){
            throw new NotEnoughParameterException();
        }
        Post post = new PostImpl(0,title,content,id, LocalDateTime.now(),0);
        postRepository.register(post);
        return "redirect:/post-list.do";
    }
}
