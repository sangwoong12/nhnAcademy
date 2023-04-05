package org.example;

import org.example.CustomException.NotEnoughMoneyException;

public class Counter {

    public int payment(Basket basket,int money){
        int totalPrice = basket.getFoods().stream().mapToInt(Food::getPrice).sum();
        System.out.println("총 가격은 "+ totalPrice + "입니다.");
        if (money > totalPrice){
            System.out.println("고객님 결제 후 잔액:"+(money-totalPrice));
            return money-totalPrice;
        }
        throw new NotEnoughMoneyException();
    }
}
