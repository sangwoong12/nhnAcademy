package com.nhnacademy.springmvcstudent.filter;


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
                @WebInitParam(name = "exclude-urls", value = "/login\n" + "/login/\n" + "/logout\n"
                        + "/WEB-INF/view/login.jsp\n")
        }
)
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
