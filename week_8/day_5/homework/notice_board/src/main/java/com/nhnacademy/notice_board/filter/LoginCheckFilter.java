package com.nhnacademy.notice_board.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class LoginCheckFilter implements Filter {
    private final Set<String> excludeUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludeUrls.addAll(List.of(("/login,/logout,/WEB-INF/views/login/login.html").split(",")));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestUri = request.getRequestURI();

        if (!excludeUrls.contains(requestUri)) {
            HttpSession session = request.getSession(false);
            if (Objects.isNull(session) || Objects.isNull(session.getAttribute("id"))) {
                RequestDispatcher rd = servletRequest.getRequestDispatcher("/login");
                rd.forward(servletRequest, servletResponse);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
