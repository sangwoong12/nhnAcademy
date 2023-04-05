package com.nhnacademy.hello;

import org.jsoup.Jsoup;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class BeautifyServlet extends HelloServlet{
    private static final Logger log = Logger.getLogger(BeautifyServlet.class.getName());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = req.getParameter("html");

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");

        try(PrintWriter out = resp.getWriter()){
            out.println(Jsoup.parse(html));
            log.info("beautify");
        }
    }
}
