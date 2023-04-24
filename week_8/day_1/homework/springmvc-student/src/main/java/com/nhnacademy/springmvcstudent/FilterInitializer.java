package com.nhnacademy.springmvcstudent;


import com.nhnacademy.springmvcstudent.filter.LoginCheckFilter;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.*;
import java.util.EnumSet;

@Order(1)
public class FilterInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        LoginCheckFilter loginCheckFilter = new LoginCheckFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);

        registerFilter(servletContext, true, "encodingFilter", encodingFilter);
        registerFilter(servletContext, true, "httpMethodFilter", new HiddenHttpMethodFilter());
        registerFilter(servletContext, true, "loginCheckFilter", loginCheckFilter);
    }

    private void registerFilter(ServletContext servletContext, boolean insertBeforeOtherFilters, String filterName, Filter filter) {
        FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
        if (registration == null) {
            throw new IllegalStateException(
                "Duplicate Filter registration for '" + filterName
                    + "'. Check to ensure the Filter is only configured once.");
        }
        registration.setAsyncSupported(true);
        EnumSet<DispatcherType> dispatcherTypes = getSecurityDispatcherTypes();
        registration.addMappingForUrlPatterns(dispatcherTypes, !insertBeforeOtherFilters, "/*");
    }

    private EnumSet<DispatcherType> getSecurityDispatcherTypes() {
        return EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.ASYNC);
    }

}
