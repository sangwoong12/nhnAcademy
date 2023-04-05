package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.item.Food;
import com.nhnacademy.nhnmart.item.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(
        name = "initServlet",
        urlPatterns = "/init"
)
@Slf4j
public class InitServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServletContext sc = getServletContext();
//
//        FoodStand foodStand = new FoodStand();
//
//
//        //상품이 추가되어도 로직이 변화없도록 구현
//        Enumeration<String> foodNames = sc.getInitParameterNames();
//
//        while (foodNames.hasMoreElements()){
//            String foodName = foodNames.nextElement();
//            String str = sc.getInitParameter(foodName);
//
//            String[] split = str.split(",");
//
//            int amount = Integer.parseInt(split[0]);
//            int price = Integer.parseInt(split[1]);
//            Food food = new Food(foodName, amount);
//
//            foodStand.add(food,price);
//
//            sc.setAttribute(foodName,food);
//        }
//        sc.setAttribute("foodStand",foodStand);
//        resp.sendRedirect("/foods");
//    }
}
