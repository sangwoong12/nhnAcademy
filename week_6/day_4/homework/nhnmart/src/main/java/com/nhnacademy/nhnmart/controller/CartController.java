package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.exception.AmountException;
import com.nhnacademy.nhnmart.init.RequestMapping;
import com.nhnacademy.nhnmart.item.BuyList;
import com.nhnacademy.nhnmart.item.BuyListRepository;
import com.nhnacademy.nhnmart.item.Food;
import com.nhnacademy.nhnmart.item.FoodStand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@RequestMapping(value = "/cart.do", method = RequestMapping.Method.POST)
public class CartController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        FoodStand foodStand = (FoodStand) req.getServletContext().getAttribute("foodStand");

        BuyListRepository buyListRepository = (BuyListRepository) req.getServletContext().getAttribute("buyListRepository");
        BuyList buyList = buyListRepository.getById(req.getSession().getId());

        //음식이 추가되어도 가능하도록 설계
        Enumeration<String> foodNames = req.getParameterNames();
        while(foodNames.hasMoreElements()){
            String foodName = foodNames.nextElement();
            int quantity = Integer.parseInt(req.getParameter(foodName));
            Food getFood = (Food) req.getServletContext().getAttribute(foodName);
            if(!foodStand.enoughFoods(getFood,quantity)){
                throw new AmountException();
            }
            //foodStand 에서 음식을 빼는 과정
            Food food = foodStand.getFoods(getFood, quantity);
            //buylist에 음식을 추가하는 과정
            buyList.add(food,quantity);
        }
        String id = (String) req.getSession().getAttribute("id");
        buyListRepository.add(id,buyList);
        return "cart.jsp";
    }
}
