package com.nhnacademy.springmvcstudent.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LoginCheckInterceptor implements HandlerInterceptor {

    private final Set<String> excludeUrls;

    public LoginCheckInterceptor(String excludeUrl) {
        this.excludeUrls = new HashSet<>(Arrays.asList(
                excludeUrl.split(",")
        ));
        System.out.println("excludeUrl:"+excludeUrl);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        if (!excludeUrls.contains(requestUri)) {
            HttpSession session = request.getSession(false);
            if (Objects.isNull(session) || Objects.isNull(session.getAttribute("user"))) {
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }
        }
        return true;
    }
}






