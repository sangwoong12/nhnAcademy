package com.nhnacademy.hello;

import com.nhnacademy.hello.util.CookieUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Objects;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

@Slf4j
public class HelloServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("init()");
        super.init(config);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        log.info("service()");
        super.service(req, res);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String title = getServletConfig().getInitParameter("title");
        String name = getServletConfig().getInitParameter("name");

        String url = getServletContext().getInitParameter("url");
        Cookie cookie = new Cookie("userName", "sangwoong");
        resp.addCookie(cookie);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        try(PrintWriter out = resp.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
                out.println("<head>");
                    out.println("<meta charset = 'UTF-8' />");
                    out.println("<title>hello servlet </title>");
                out.println("</head>");
                out.println("<body>");
                    out.printf("<p>hello %s %s</p>\n",title,name);
                out.println("</body>");
            out.println("</html>");
        }
        log.info("doGet()");
    }

    @Override
    public void destroy() {
        log.info("destroy()");
        super.destroy();
    }
}