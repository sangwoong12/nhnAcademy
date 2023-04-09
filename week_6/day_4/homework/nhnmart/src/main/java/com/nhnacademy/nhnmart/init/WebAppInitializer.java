package com.nhnacademy.nhnmart.init;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

@HandlesTypes(value = {
        com.nhnacademy.nhnmart.controller.Command.class
})
public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        ControllerFactory controllerFactory = new ControllerFactory();
        controllerFactory.init(set);

        servletContext.setAttribute("controllerFactory",controllerFactory);
    }
}
