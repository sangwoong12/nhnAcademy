package com.nhnacademy.hello;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class MultiServlet extends HelloServlet{
    private static final Logger log = Logger.getLogger(MultiServlet.class.getName());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");

        try(PrintWriter out = resp.getWriter()){
            String subjects[] = req.getParameterValues("subject");
            for (String subject : subjects) {
                out.println(subject);
            }
        }catch (Exception e){
            log.info("multiServlet error:" + e.getMessage());


        }
    }
}
