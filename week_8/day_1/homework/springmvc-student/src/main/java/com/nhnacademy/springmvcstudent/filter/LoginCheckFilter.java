package com.nhnacademy.springmvcstudent.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LoginCheckFilter implements Filter {
    private final Set<String> excludeUrls = new HashSet<>(Arrays.asList(
            "/login", "/login/", "/logout"
            , "/WEB-INF/view/login.jsp"
    ));

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestUri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (!excludeUrls.contains(requestUri)) {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);
            if (Objects.isNull(session) || Objects.isNull(((HttpServletRequest) servletRequest).getSession().getAttribute("user"))) {
                RequestDispatcher rd = servletRequest.getRequestDispatcher("/login");
                rd.forward(servletRequest, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
