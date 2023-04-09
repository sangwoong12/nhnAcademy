package com.nhnacademy.nhnmart.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@WebFilter(
        filterName = "loginCheckFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclude-urls",value = "/login\n" + "/logout\n" + "/login.jsp")
        }
)
@Slf4j
public class LoginCheckFilter implements Filter {
    private final Set<String> excludeUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("exclude-urls");
        Arrays.stream(urls.split("\n"))
                .map(String::trim)
                .forEach(excludeUrls::add);
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestUri = ((HttpServletRequest) servletRequest).getRequestURI();
        if(!excludeUrls.contains(requestUri)){
            System.out.println("로그인 관련 아니면 무조건 들어와");
            HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);
            if(Objects.isNull(session)){
                System.out.println("세션이 널이면 무조건 들어와");
                RequestDispatcher rd = servletRequest.getRequestDispatcher("/login.jsp");
                rd.forward(servletRequest,servletResponse);
            } else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
