package com.nhnacademy.servlet;

import com.nhnacademy.nhnmart.Food;
import com.nhnacademy.nhnmart.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@Slf4j
public class InitServlet extends HttpServlet {
    /**
     * getInitParameterNames 로 context-param 을 모두 읽어 foodStand 를 만들어 attribute
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = getServletContext();

        FoodStand foodStand = new FoodStand();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        //상품이 추가되어도 로직이 변화없도록 구현
        Enumeration<String> foodNames = sc.getInitParameterNames();

        while (foodNames.hasMoreElements()){
            String foodName = foodNames.nextElement();
            String str = sc.getInitParameter(foodName);
            //데이터를 가격, 수량 순으로 등록하여 아래와 같이 접근
            String[] split = str.split(",");
            Food food = new Food(foodName, Integer.parseInt(split[0]));

            for(int i = 0; i < Integer.parseInt(split[1]); i++){
                foodStand.add(new Food(foodName,Integer.parseInt(split[0])));
            }
            sc.setAttribute(foodName,food);
        }
        sc.setAttribute("foodStand",foodStand);

        try(PrintWriter out = resp.getWriter()){
            getHtml(out);
        } catch (IOException e){
            log.error("/init {}",e.getMessage());
        }
    }

    private static void getHtml(PrintWriter out) {

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("<link rel='stylesheet' href='nhnmart.css'>");
        out.println("</head>");
        out.println("<body>");
            out.println("<div>");
                out.println("<a href='/foods'>상품 목록</a>");
            out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
