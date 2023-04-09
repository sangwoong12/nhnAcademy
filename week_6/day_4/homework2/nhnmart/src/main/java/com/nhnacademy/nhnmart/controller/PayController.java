package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.exception.NotEnoughMoneyException;
import com.nhnacademy.nhnmart.init.RequestMapping;
import com.nhnacademy.nhnmart.item.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@RequestMapping(value = "/pay.do",method = RequestMapping.Method.POST)
public class PayController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = (String) req.getSession().getAttribute("id");
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        BuyListRepository buyListRepository = (BuyListRepository) req.getServletContext().getAttribute("buyListRepository");
        BuyList buyList = buyListRepository.getById(id);
        //체크한 물품에 대해서 받아옴
        Enumeration<String> foodNames = req.getParameterNames();
        List<Food> payFoodNames = new ArrayList<>();
        //총가격은 form으로 받아와도 되지만 사용자가 데이터를 수정하여 보낼수도 있기 때문에 buylist 에서 계산
        while (foodNames.hasMoreElements()){
            //음식을 전부 보내서 한번에 처리하기 위해 간단하게 스트링으로 처리
            payFoodNames.add((Food) req.getServletContext().getAttribute(foodNames.nextElement()));
        }
        int totalPrice = buyList.getTotalPrice(payFoodNames);

        User getuser = userRepository.getuser(id);
        int money = getuser.getMoney();
        //돈이 부족할시 발생하는 에러
        if(totalPrice > money){
            throw new NotEnoughMoneyException();
        }
        //결제가 가능하면 buylist에서 결제가 되는 음식을 제거
        buyList.removeFood(payFoodNames);
        req.setAttribute("totalPrice",totalPrice);

        //결제가 끝나면 user 의 돈을 수정
        getuser.setMoney(money - totalPrice);

        int balance = getuser.getMoney();

        req.setAttribute("balance",balance);
        return "/pay.jsp";
    }
}
