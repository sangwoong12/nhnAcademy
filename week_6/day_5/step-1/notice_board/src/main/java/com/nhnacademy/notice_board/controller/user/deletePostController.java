package com.nhnacademy.notice_board.controller.user;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.init.RequestMapping;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import com.nhnacademy.notice_board.repository.user.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/delete-post.do")
public class deletePostController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        String id = req.getParameter("id");

        if (!postRepository.existById(Long.parseLong(id))) {
            throw new RuntimeException("존재하지 않는 포스트입니다.");
        } else {
            postRepository.remove(Long.parseLong(id));
            return "redirect:/post-list.do";
        }
    }
}
