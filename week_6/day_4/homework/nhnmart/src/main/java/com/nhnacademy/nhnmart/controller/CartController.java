package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.init.RequestMapping;
import com.nhnacademy.nhnmart.item.BuyList;
import com.nhnacademy.nhnmart.item.BuyListRepository;
import com.nhnacademy.nhnmart.item.Food;
import com.nhnacademy.nhnmart.item.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@RequestMapping(value = "/cart.do", method = RequestMapping.Method.POST)
@Slf4j
public class CartController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        FoodStand foodStand = (FoodStand) req.getServletContext().getAttribute("foodStand");

        BuyList buyList = new BuyList();
        //음식이 추가되어도 가능하도록 설계
        Enumeration<String> foodNames = req.getParameterNames();
        while(foodNames.hasMoreElements()){
            String foodName = foodNames.nextElement();
            int amount = Integer.parseInt(req.getParameter(foodName));
            Food getFood = foodStand.getByFoodName(foodName);
            log.info("getFood:"+getFood.getName());
            if(!foodStand.enoughFoods(getFood,amount)){
                throw new RuntimeException("주문하신 음식의 갯수가 너무 많습니다.");
            }
            //foodStand 에서 음식을 빼는 과정
            Food food = foodStand.getFoods(getFood, amount);
            //buylist에 음식을 추가하는 과정
            buyList.add(food,amount);
        }

        BuyListRepository buyListRepository = (BuyListRepository) req.getServletContext().getAttribute("buyListRepository");
        String id = (String) req.getSession().getAttribute("id");
        buyListRepository.add(id,buyList);
        log.info("요청한 바이리스트 :"+buyList.getBuyList().toString());
        return "cart.jsp";
    }
}
