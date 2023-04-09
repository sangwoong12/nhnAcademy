package com.nhnacademy.notice_board.controller.user;

import com.nhnacademy.notice_board.controller.Command;
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
            throw new RuntimeException("존재하지 않는 아이디입니다.");
        } else {
            Post post = postRepository.getPost(Long.parseLong(id));
            if(post.getWriterUserId() != req.getSession().getAttribute("id")){
                throw new RuntimeException("수정할 수 있는 권한이 없습니다.");
            }
            req.setAttribute("post",post);
            return "/user/edit-post.jsp";
        }
    }
}
