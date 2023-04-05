package com.nhnacademy.hello.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Optional;

public class CookieUtils {
    private CookieUtils(){
        throw new IllegalStateException("Utility class");
    }
    public static Cookie getCookie(HttpServletRequest req,String name){
//        Optional.ofNullable(req.getCookies())
//                .flatMap(cookies -> Arrays.stream(cookies)
//                        .filter(c->c.getName().equals(name))
//                        .findFirst())
//                .orElse(null);
        return Arrays.stream(req.getCookies())
                .filter(c->c.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
