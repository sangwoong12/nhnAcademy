package com.nhnacademy.nhnmart.init;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    enum Method{
        POST,GET
    }
    //path
    String value();
    
    Method method() default Method.GET;
}
