package com.nhnacademy.nhnmart.initalizer;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

@HandlesTypes(value = {
        javax.servlet.http.HttpServlet.class,
        javax.servlet.Filter.class,
        javax.servlet.ServletContextListener.class,
        javax.servlet.http.HttpSessionListener.class
})
public class WebAppInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("apple","20,2000");
        servletContext.setAttribute("eggs","5,2000");
        servletContext.setAttribute("onion","2,1000");
        servletContext.setAttribute("greenOnion","10,500");
    }
}
