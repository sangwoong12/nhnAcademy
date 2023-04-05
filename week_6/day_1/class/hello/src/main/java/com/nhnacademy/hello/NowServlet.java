package com.nhnacademy.hello;

import com.nhnacademy.hello.util.CookieUtils;
import com.nhnacademy.hello.util.CounterUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Slf4j
public class NowServlet extends HelloServlet{

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        log.info("service()");
        super.service(req, res);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String userName = CookieUtils.getCookie(req, "userName").getValue();

        try(PrintWriter out = resp.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset = 'UTF-8' />");
            out.println("<title>hello servlet </title>");
            out.println("</head>");
            out.println("<body>");
            out.print("<p>"+LocalDateTime.now()+"</p>\n");
            out.print("<p>"+userName+"</p>\n");
            CounterUtils.increaseCounter(getServletContext());
            log.info(req.getServletContext().getAttribute("count").toString());
            out.print("<p>" + req.getServletContext().getAttribute("count") + "</p>\n");
            out.println("</body>");
            out.println("</html>");
        }
        log.info("doGet()");
    }
}
