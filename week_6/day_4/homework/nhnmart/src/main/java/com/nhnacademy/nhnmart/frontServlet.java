package com.nhnacademy.nhnmart;

import com.nhnacademy.nhnmart.controller.Command;
import com.nhnacademy.nhnmart.init.ControllerFactory;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "*.do")
public class frontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX = "redirect";
    private ControllerFactory controllerFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        controllerFactory = (ControllerFactory) config.getServletContext().getAttribute("controllerFactory");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        try {
            String method = req.getMethod();
            String servletPath = req.getServletPath();
            log.error("요청정보 : "+servletPath + ":"+method);
            Command command = (Command) controllerFactory.getBean(method,servletPath);
            log.error(command.toString());
            String view = command.execute(req,resp);
            log.error("view 정보 : {}",view);
            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length() + 1));
                String url = view.substring(REDIRECT_PREFIX.length() + 1);
                resp.sendRedirect(url);

            } else {
                RequestDispatcher rd = req.getRequestDispatcher(view);//jsp 한테 위임
                log.error("view : {}",view);
                rd.include(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("status_code", "400 Bad Request");
            req.setAttribute("exception_type", "exception_type");
            req.setAttribute("message", e.getMessage());
            req.setAttribute("exception", "exception");
            req.setAttribute("request_uri", req.getRequestURI());
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req,resp);
        }
    }
}
