package com.nhnacademy.hello.filter;

import com.nhnacademy.hello.util.CounterUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(
        filterName = "counterFilter",
        urlPatterns = "/*"
)
@Slf4j
public class CountFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CounterUtils.increaseCounter(servletRequest.getServletContext());
        long count = (long)servletRequest.getServletContext().getAttribute("counter");
        log.info(String.valueOf(count));
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
