package com.nhnacademy.notice_board.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;


public class AuthCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String id = (String) request.getSession().getAttribute("id");
        if (request.getRequestURI().contains("/admin") && Objects.nonNull(id)) {
            if (!id.equals("admin")) {
                //유저가 /admin 으로 접근시 /user 로 보냄
                RequestDispatcher rd = servletRequest.getRequestDispatcher("/user");
                rd.forward(servletRequest, servletResponse);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
