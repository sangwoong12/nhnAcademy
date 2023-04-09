package com.nhnacademy.nhnmart.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;
@Slf4j
public final class CookieUtils {
    
    private CookieUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Cookie getCookie(HttpServletRequest req, String name) {

        for(Cookie cookie : req.getCookies()){
            if(cookie.getName().equals(name)){
                log.error("{}:{}", cookie.getName(), cookie.getValue() );
            }
        }

        return Optional.ofNullable(req.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(c -> c.getName().equals(name))
                        .findFirst())
                .orElse(null);
    }
}
