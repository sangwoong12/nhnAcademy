package com.nhnacademy.hello;

import com.nhnacademy.hello.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@Slf4j
public class ReadCookieServlet extends HelloServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie cookie = CookieUtils.getCookie(req,"locale");

        if(Objects.isNull(cookie)){
            resp.sendError(500,"cookie not found");
            return ;
        }

        String locale = cookie.getValue();

        String helloValue = ResourceBundle.getBundle("message", new Locale(locale)).getString("hello");

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        try(PrintWriter out = resp.getWriter()){
            out.println(helloValue);
        }catch (IOException e){
            log.error("read-cookie error: "+ e.getMessage());
        }

    }
}
