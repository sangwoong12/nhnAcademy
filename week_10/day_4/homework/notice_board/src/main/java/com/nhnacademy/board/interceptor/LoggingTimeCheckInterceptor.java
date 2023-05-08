package com.nhnacademy.board.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggingTimeCheckInterceptor implements HandlerInterceptor {
    ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long beforeTime = System.currentTimeMillis();
        threadLocal.set(beforeTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        long beforeTime = threadLocal.get();
        long result = System.currentTimeMillis() - beforeTime;
        threadLocal.remove();

        if (modelAndView != null && response.getStatus() != 302 && response.getStatus() != 301) {
            //TODO redirect 의 경우 총 2번의 요청이 있는데 시간을 총 합쳐서 넘겨주는 방법을 찾고 문제해결.
            request.getSession().setAttribute("time",result);
        }
    }
}
