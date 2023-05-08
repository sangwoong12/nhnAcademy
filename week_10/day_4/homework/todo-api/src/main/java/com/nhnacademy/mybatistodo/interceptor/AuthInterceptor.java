package com.nhnacademy.mybatistodo.interceptor;

import com.nhnacademy.mybatistodo.exception.UnauthorizedUserException;
import com.nhnacademy.mybatistodo.share.UserIdStore;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userId = request.getHeader("X-USER-ID");
        if (!StringUtils.hasText(userId)) {
            throw new UnauthorizedUserException();
        }
        UserIdStore.setUserId(userId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserIdStore.remove();
    }
}
