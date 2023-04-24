package com.nhnacademy.board.springmvcboard.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 방문자 수 : 게시물 조회 하거나 사용자 조회 하는 경우
 */
@WebFilter(
        filterName = "VisitorsCountFilter",
        urlPatterns = {"/post-view.do" , "/user-info.do"}
)
public class VisitorsCountFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long count = (Long) servletRequest.getServletContext().getAttribute("visitorsCount");
        count++;
        servletRequest.getServletContext().setAttribute("visitorsCount",count);
        filterChain.doFilter(servletRequest,servletResponse);
    }

}
