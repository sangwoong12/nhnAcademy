package com.nhnacademy.board;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Order(3)
public class SessionInitializer implements WebApplicationInitializer {
    private static int activeUsers = 0;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        servletContext.addListener(new HttpSessionListener() {
            @Override
            public synchronized void sessionCreated(HttpSessionEvent se) {
                activeUsers++;
                se.getSession().setAttribute("counter", activeUsers);
            }

            @Override
            public synchronized void sessionDestroyed(HttpSessionEvent se) {
                activeUsers--;
                se.getSession().setAttribute("counter", activeUsers);
            }
        });
    }
}