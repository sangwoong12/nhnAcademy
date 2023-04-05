package com.nhnacademy.servlet;

import com.nhnacademy.nhnmart.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Slf4j
public class FoodsServlet extends HttpServlet{
    /**
     * 로그인, init 을 하지 않았다면 돌려보내고, 했을 경우 foodStand 정보를 출력하고 장바구니에 물품을 담는 form 을 만든다.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FoodStand foodStand = (FoodStand) getServletContext().getAttribute("foodStand");
        if (Objects.isNull(req.getSession(false))) {
            log.info("로그인 필요");
            resp.sendRedirect("/login");
        }
        else if (Objects.isNull(foodStand)) { //null 일경우 init 과정을 생략하였기 때문에 강제로 init 으로 보낸다.
            log.info("접속 불가");
            resp.sendRedirect("/init");
        } else {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");

            try(PrintWriter out = resp.getWriter()){
                getHtml(foodStand, out);
            } catch (IOException e){
                log.error("/foods : {}",e.getMessage(),e);
            }
        }
    }

    private void getHtml(FoodStand foodStand, PrintWriter out) {
        StringBuilder sb = new StringBuilder();
        sb.append("<form method='post' action='/cart'>");
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        out.println("<link rel='stylesheet' href='nhnmart.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div>");
        out.println("<ui>");
        Enumeration<String> foodNames = getServletContext().getInitParameterNames();
        while (foodNames.hasMoreElements()){
            String foodName = foodNames.nextElement();
            int count = (int) foodStand.getFoods().stream().filter(food -> food.getName().equals(foodName)).count();
            out.println("<li>식품 이름 ["+foodName +"] : 갯수 ["+count+"]</li>");
            sb.append(foodName+"<input type='number' name='"+foodName+"' required/></br>");
        }
        sb.append("<button type='submit'>장바구니에 담기</button>");
        sb.append("</form>");

        out.println("</ui>");
        out.println("</div>");
        out.println("<div>");
        out.println(sb);
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
