package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.init.RequestMapping;
import com.nhnacademy.nhnmart.item.FoodStand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/foods.do")
public class foodFormController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        FoodStand foodStand = (FoodStand) req.getServletContext().getAttribute("foodStand");
        req.setAttribute("foodStand",foodStand.getFoodMap());
        return "foodsForm.jsp";
    }
}
