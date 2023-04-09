package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.item.Baskets;
import com.nhnacademy.nhnmart.item.BuyList;
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
import java.util.Enumeration;
import java.util.Map;

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
            Enumeration<String> parameterNames = req.getParameterNames();
            while(parameterNames.hasMoreElements()){
                System.out.println("value:"+parameterNames.nextElement());
            }
            int amount = Integer.valueOf(req.getParameter(foodName));
            buyList.add(food,amount);
        }
        //검증 단계
        Map<Food, Integer> list = buyList.getBuyList();
        Map<Food, Integer> foodStandFoodList = foodStand.getFoodList();
        boolean ok = true;
        for (Food food : foodStandFoodList.keySet()) {
            Food f = (Food) getServletContext().getAttribute(food.getName());
            if(foodStandFoodList.get(f) < list.get(f)){
                resp.sendRedirect("/");
                ok = false;
            }
        }
        if(ok){
            //foodStand 물량 수정
            for (Food food : foodStandFoodList.keySet()) {
                Food f = (Food) getServletContext().getAttribute(food.getName());
                foodStandFoodList.put(f,foodStandFoodList.get(f)-list.get(f));
            }
            //TODO 1: Baskets 에 유저 아이디를 통해 등록
            Baskets baskets = (Baskets) getServletContext().getAttribute("baskets");
            String id =(String) req.getSession().getAttribute("id");
            System.out.println(id);
            baskets.add(buyList,id);

            resp.sendRedirect("/cartHome.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Baskets baskets = (Baskets) getServletContext().getAttribute("baskets");
        String id =(String) req.getSession(false).getAttribute("id");

        BuyList buyList = baskets.getBaskets().get(id);
        Map<Food, Integer> buyList1 = buyList.getBuyList();

        req.setAttribute("buyList",buyList1);

        int totalPrice = buyList1.keySet().stream()
                .mapToInt(food -> food.getPrice() * buyList1.get(food)).sum();

        req.setAttribute("totalPrice",totalPrice);
        RequestDispatcher rd = req.getRequestDispatcher("/cart.jsp");
        rd.forward(req,resp);
    }
}
