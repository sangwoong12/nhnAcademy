package com.nhnacademy.notice_board.filter;

import com.nhnacademy.notice_board.exception.NotFoundPostException;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(
        filterName = "ViewCountFilter",
        urlPatterns = "/post-view.do"
)
public class ViewCountFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)  servletRequest;
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        String id = req.getParameter("id");
        if(!postRepository.existById(Long.parseLong(id))){
            throw new NotFoundPostException();
        }
        //조회수 ++
        postRepository.getPost(Long.parseLong(id)).increaseViewCount();
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
