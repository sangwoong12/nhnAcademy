package com.nhnacademy.notice_board.controller.user;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.init.RequestMapping;
import com.nhnacademy.notice_board.item.post.Post;
import com.nhnacademy.notice_board.repository.post.PostRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/edit-post.do" ,method = RequestMapping.Method.POST)
public class EditPostController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        System.out.println(id+":"+title+":"+content);
        if(id == null || title == null || content == null){
            throw new RuntimeException("id, title, content는 필수 입력값입니다.");
        }
        Post post = postRepository.getPost(Long.parseLong(id));
        if(post.getWriterUserId() != req.getSession().getAttribute("id")){
            throw new RuntimeException("수정할 수 있는 권한이 없습니다.");
        }
        post.setContent(content);
        post.setTitle(title);

        return "redirect:/post-view.do?id="+id;

    }
}
