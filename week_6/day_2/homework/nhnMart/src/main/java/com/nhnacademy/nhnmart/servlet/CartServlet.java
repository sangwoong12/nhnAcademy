package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.item.Basket;
import com.nhnacademy.nhnmart.item.BuyList;
import com.nhnacademy.nhnmart.item.Food;
import com.nhnacademy.nhnmart.item.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
            Food food = (Food) req.getAttribute(foodName);
            int amount = Integer.valueOf(req.getParameter(foodName));
            buyList.add(food,amount);
        }
        for (Food food : foodStand.getFoodStand().keySet()) {

        }
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Basket basket = (Basket) getServletContext().getAttribute("basket");
//
//        if(Objects.isNull(req.getSession(false))){
//            log.info("로그인 필요");
//            resp.sendRedirect("/login");
//        } else if (Objects.isNull(basket)) { //null 일경우 cart post 과정을 생략하였기 때문에 강제로 init 으로 보낸다.
//            resp.sendRedirect("/init");
//        } else {
//            resp.setContentType("text/html");
//            resp.setCharacterEncoding("UTF-8");
//            //같은 물품 별로 <이름,수량>
//            Map<String, Integer> map = basket.getFoods().stream()
//                    .collect(Collectors.toMap(Food::getName, f -> 1, Integer::sum));
//
//            try(PrintWriter out = resp.getWriter()){
//                getHtml(map, out);
//            }catch (IOException e){
//                log.info("/cart {}",e.getMessage());
//            }
//        }
//    }
}
