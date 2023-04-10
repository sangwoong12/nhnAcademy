package com.nhnacademy.notice_board.controller.user;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.exception.NotFoundIdException;
import com.nhnacademy.notice_board.exception.NotPermissionException;
import com.nhnacademy.notice_board.init.RequestMapping;
import com.nhnacademy.notice_board.item.post.Post;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/edit-post.do")
@Slf4j
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
