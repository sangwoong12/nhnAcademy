package com.nhnacademy.board.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;


@Slf4j
public class ViewCountFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getRequestURI().equals("/user/view-post")) {
            Cookie[] cookies = req.getCookies();
            boolean ok = Arrays.stream(cookies)
                    .noneMatch(cookie -> cookie.getName().equals(req.getParameter("postId")));
            if (ok) {
                req.setAttribute("count", "ok");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
