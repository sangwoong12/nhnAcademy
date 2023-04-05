package com.nhnacademy.hello.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
public class LoginCheckFilter implements Filter {
    String[] excludeUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String initParameter = filterConfig.getInitParameter("exclude-urls");
        excludeUrl = initParameter.split(System.lineSeparator());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI().trim();
        //TODO 1: 이 방식보단 set 으로 키를 등록하여 constain으로 접근하는게 좋아보인다.
        boolean isExcluded = Arrays.stream(excludeUrl).anyMatch(url -> url.trim().equals(requestURI));

        if (!isExcluded) {
            HttpSession session = request.getSession(false);
            if (session == null) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendRedirect("/login.html");
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
