package com.nhnacademy.notice_board.filter;

import com.nhnacademy.notice_board.controller.exception.NotFoundIdException;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(
        filterName = "ViewCountFilter",
        urlPatterns = "/post-view.do"
)
@Slf4j
public class ViewCountFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)  servletRequest;
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        String id = req.getParameter("id");
        if(!postRepository.existById(Long.parseLong(id))){
            throw new NotFoundIdException();
        }
        postRepository.increaseCount(postRepository.getPost(Long.parseLong(id)));
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
