package com.nhnacademy.hello;

import com.nhnacademy.hello.util.CounterUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.logging.Logger;

public class CounterServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(CounterServlet.class.getName());

    private long counter;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        counter = Optional.ofNullable(config.getInitParameter("counter"))
                .map(Long::parseLong)
                .orElse(0l);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;

        try(PrintWriter out = resp.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("</head>");
            out.println("<body>");
            CounterUtils.increaseCounter(getServletContext());
            out.print("<p>" + req.getServletContext().getAttribute("count") + "</p>\n");
            out.println("</body>");
            out.println("</html>");
        }catch (IOException ex){
            log.info(ex.getMessage());
        }
    }
}