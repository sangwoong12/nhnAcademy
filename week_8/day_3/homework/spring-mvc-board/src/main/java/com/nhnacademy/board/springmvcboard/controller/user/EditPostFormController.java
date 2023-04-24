package com.nhnacademy.board.springmvcboard.controller.user;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.controller.exception.NotFoundIdException;
import com.nhnacademy.notice_board.controller.exception.NotPermissionException;
import com.nhnacademy.notice_board.init.RequestMapping;
import com.nhnacademy.notice_board.item.post.Post;
import com.nhnacademy.notice_board.repository.post.PostRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/edit-post.do")
public class EditPostFormController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        String id = req.getParameter("id");

        if (!postRepository.existById(Long.parseLong(id))) {
            throw new NotFoundIdException();
        } else {
            Post post = postRepository.getPost(Long.parseLong(id));
            if(!post.getWriterUserId().equals(req.getSession().getAttribute("id"))){
                throw new NotPermissionException();
            }
            req.setAttribute("post",post);
            return "/user/edit-post.jsp";
        }
    }
}
