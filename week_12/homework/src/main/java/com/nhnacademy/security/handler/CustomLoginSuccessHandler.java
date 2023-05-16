package com.nhnacademy.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        session.setAttribute("username", authentication.getName());

        Cookie cookie = new Cookie("SESSION", session.getId());
        response.addCookie(cookie);

        User user = (User) authentication.getPrincipal();

        Iterator<GrantedAuthority> iterator = user.getAuthorities().iterator();

        if (iterator.hasNext()) {
            if (iterator.next().getAuthority().equals("ROLE_ADMIN")) {
                response.sendRedirect("/home/admin");
            }
        } else {
            response.sendRedirect("/home/member");
        }
    }
}
