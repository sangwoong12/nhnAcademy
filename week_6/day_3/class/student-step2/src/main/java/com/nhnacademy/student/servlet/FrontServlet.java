package com.nhnacademy.student.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX="redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.error("frontServlet start");

        //todo 공통 처리 - 응답 content-type, character encoding 지정.
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        try{
            //실제 요청 처리할 servlet 을 결정
            String servletPath = resolveServlet(req.getServletPath());
            log.error("servletPath: {}",servletPath);
            RequestDispatcher rd = req.getRequestDispatcher(servletPath);
            //동시에 동작하는 쓰레드의 동작이 끝날때 까지 기다림.
            rd.include(req, resp);
            String view = (String) req.getAttribute("view"); //front servlet 을 제외한 나머지 servlet은 forward,redirect 하지않고 view 를 넘김
            if (view.startsWith(REDIRECT_PREFIX)) {
                String url = view.substring(REDIRECT_PREFIX.length() + 1);
                log.error("redirect-url : {}", url);
                // todo  `redirect:`로 시작하면 redirect 처리.
                resp.sendRedirect(url);
            } else {
                //todo redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include시킴.
                rd = req.getRequestDispatcher(view);//jsp 한테 위임
                log.error("view : {}",view);
                rd.include(req, resp);
            }
        }catch(Exception ex){
            //todo 공통 error 처리 - ErrorServlet 참고해서 처리

            //todo  forward - /error.jsp
            RequestDispatcher rd = req.getRequestDispatcher("/error");
            rd.forward(req,resp);
        }
    }

    private String resolveServlet(String servletPath){
        String processingServlet = null;

        //todo 실행할 servlet 결정하기
        if("/student/list.do".equals(servletPath)){
            processingServlet = "/student/list";
        }else if("/student/view.do".equals(servletPath)){
            processingServlet = "/student/view";
        }else if("/student/delete.do".equals(servletPath)){
            processingServlet = "/student/delete";
        }else if("/student/update.do".equals(servletPath)){
            processingServlet = "/student/update";
        }else if("/student/register.do".equals(servletPath)){
            processingServlet = "/student/register";
        }else if("/error.do".equals(servletPath)){
            processingServlet = "/error";
        }
        return processingServlet;
    }

}