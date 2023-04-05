package com.nhnacademy.hello;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Slf4j
public class ResponseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.setBufferSize(1024);
        log.info("default buffer size : {}", resp.getBufferSize());

        String userId = req.getParameter("userId");
        log.info("userId:{}",userId);

        if (userId == null || userId.isEmpty()) {
            resp.reset();
            resp.setStatus(500);
            resp.sendError(500, "name is empty");
            return;
        }
        try(PrintWriter out = resp.getWriter()){

            out.println("locale=" + req.getLocale());
            out.println("parameter name=" + req.getParameter("name"));
            //out.flush();
//            out.close();

            String redirect = req.getParameter("redirect");

            if(Objects.nonNull(redirect)){
                resp.sendRedirect(redirect);
                return;
            }

            out.println("content type:" + req.getContentType());
            out.println("content length:" + req.getContentLength());
            out.println("method=" + req.getMethod());
            out.println("request uri=" + req.getRequestURI());
            out.println("User-Agent header=" + req.getHeader("User-Agent"));
//            resp.setStatus(500);
//            resp.sendError(500,"server Error");
        }catch (Exception e){
            log.error("/req : {}",e.getMessage(),e);
        }
    }
}
