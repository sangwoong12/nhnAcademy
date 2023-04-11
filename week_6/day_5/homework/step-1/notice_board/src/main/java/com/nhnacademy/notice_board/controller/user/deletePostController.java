package com.nhnacademy.notice_board.controller.user;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.exception.NotFoundPostException;
import com.nhnacademy.notice_board.init.RequestMapping;
import com.nhnacademy.notice_board.repository.post.PostRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/delete-post.do")
public class deletePostController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        String id = req.getParameter("id");

        if (!postRepository.existById(Long.parseLong(id))) {
            throw new NotFoundPostException();
        } else {
            postRepository.remove(Long.parseLong(id));
            return "redirect:/post-list.do";
        }
    }
}
