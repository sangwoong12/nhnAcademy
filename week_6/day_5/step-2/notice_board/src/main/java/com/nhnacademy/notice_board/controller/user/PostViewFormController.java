package com.nhnacademy.notice_board.controller.user;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.init.RequestMapping;
import com.nhnacademy.notice_board.repository.post.PostRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/post-view.do")
public class PostViewFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        String id = req.getParameter("id");
        if(id == null){
            throw new RuntimeException("id 값은 필수입니다.");
        }
        req.setAttribute("post",postRepository.getPost(Long.parseLong(id)));
        req.setAttribute("id",req.getSession().getAttribute("id"));
        return "/user/post-view.jsp";
    }
}
