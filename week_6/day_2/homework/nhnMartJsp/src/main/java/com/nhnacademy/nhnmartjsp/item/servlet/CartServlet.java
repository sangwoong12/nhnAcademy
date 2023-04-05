package com.nhnacademy.nhnmartjsp.item.servlet;

import com.nhnacademy.nhnmartjsp.item.item.BuyList;
import com.nhnacademy.nhnmartjsp.item.item.Food;
import com.nhnacademy.nhnmartjsp.item.item.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet(
        name = "cartServlet",
        urlPatterns = "/cart"
)
@Slf4j
public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FoodStand foodStand = (FoodStand) getServletContext().getAttribute("foodStand");

        BuyList buyList = new BuyList();

        Enumeration<String> foodNames = getServletContext().getInitParameterNames();
        while (foodNames.hasMoreElements()) {
            String foodName = foodNames.nextElement();
            Food food = (Food) getServletContext().getAttribute(foodName);
            int amount = Integer.valueOf(req.getParameter(foodName));
            buyList.add(food,amount);
        }
        //검증 단계
        Map<Food, Integer> list = buyList.getBuyList();
        Map<Food, Integer> foodStandFoodList = foodStand.getFoodList();
        for (Food food : foodStandFoodList.keySet()) {
            Food f = (Food) getServletContext().getAttribute(food.getName());
            if(foodStandFoodList.get(f) < list.get(f)){
                resp.sendRedirect("/");
            }
        }
        //foodStand 물량 수정
        for (Food food : foodStandFoodList.keySet()) {
            Food f = (Food) getServletContext().getAttribute(food.getName());
            foodStandFoodList.put(f,foodStandFoodList.get(f)-list.get(f));
        }
        //TODO 1: Baskets 에 유저 아이디를 통해 등록

        resp.sendRedirect("/cart");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/cart.jsp");
    }
}
