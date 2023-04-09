package com.nhnacademy.student.init;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

@HandlesTypes(value = {
        com.nhnacademy.student.controller.Command.class
})
public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        ControllerFactory controllerFactory = new ControllerFactory();
        controllerFactory.init(set);

        servletContext.setAttribute("controllerFactory",controllerFactory);
    }
}
