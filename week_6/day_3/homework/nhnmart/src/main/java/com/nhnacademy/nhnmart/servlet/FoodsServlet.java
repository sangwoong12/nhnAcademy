package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.item.Food;
import com.nhnacademy.nhnmart.item.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(
        name = "foodsServlet",
        urlPatterns = "/foods"
)
@Slf4j
public class FoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FoodStand foodStand = (FoodStand) getServletContext().getAttribute("foodStand");
        Map<Food, Integer> foodList = foodStand.getFoodList();
        req.setAttribute("foodList",foodList);
        System.out.println(foodList.toString());
        RequestDispatcher rd = req.getRequestDispatcher("/foods.jsp");
        rd.forward(req,resp);
    }
}
