package com.nhnacademy.hello.util;

import javax.servlet.ServletContext;

public class CounterUtils {
    private CounterUtils() {
        throw new IllegalStateException("Utility class");
    }
    public static void increaseCounter(ServletContext servletContext){
        if(servletContext.getAttribute("count") == null){
            servletContext.setAttribute("count",1);
        }
        int count = (int) servletContext.getAttribute("count");
        count++;
        servletContext.setAttribute("count",count);
    }
}
