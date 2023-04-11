package com.nhnacademy.student;

import com.nhnacademy.student.controller.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.RequestDispatcher.*;

@Slf4j
@WebServlet(urlPatterns = "*.do")
public class frontServlet extends HttpServlet {
    
    private static final String REDIRECT_PREFIX = "redirect";
    private static final String GET = "GET";
    private static final String POST = "POST";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        try {
            String method = req.getMethod();
            String servletPath = req.getServletPath();

            log.error(servletPath + ":"+method);
            Command command = resolveCommand(servletPath, method);
            String view = command.execute(req,resp);

            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length() + 1));
                String url = view.substring(REDIRECT_PREFIX.length() + 1);
                resp.sendRedirect(url);

            } else {
                RequestDispatcher rd = req.getRequestDispatcher(view);//jsp 한테 위임
                log.error("view : {}",view);
                rd.include(req, resp);
            }
        } catch (Exception e){
            log.error("Exception 발생");
            req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
            req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
            req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
            req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
            req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req,resp);
        }
    }
    public Command resolveCommand(String path, String method){
        Command command;
        if (path.equals("/student/list.do") && method.equals(GET)) {
            command = new StudentListFormController();
        } else if (path.equals("/student/register.do") && method.equals(GET)) {
            command = new StudentRegisterFromController();
        } else if (path.equals("/student/register.do") && method.equals(POST)) {
            command = new StudentRegisterController();
        } else if (path.equals("/student/update.do") && method.equals(GET)) {
            command = new StudentUpdateFromController();
        } else if (path.equals("/student/update.do") && method.equals(POST)) {
            command = new StudentUpdateController();
        } else if (path.equals("/student/view.do") && method.equals(GET)) {
            command = new StudentViewController();
        } else if (path.equals("/student/delete.do") && method.equals(POST)){
            command = new StudentDeleteController();
        } else {
            throw new RuntimeException("존재하지 않는 URL 요청입니다.");
        }
        return command;
    }
}
