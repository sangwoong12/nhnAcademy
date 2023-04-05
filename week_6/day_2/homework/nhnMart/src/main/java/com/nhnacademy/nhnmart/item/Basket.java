package com.nhnacademy.nhnmart.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
    private final Map<Food,Integer> basket = new HashMap<>();

    public void add(Food food, int count){
        basket.put(food,count);
    }
    public Map<Food,Integer> getBasket(){
        return basket;
    }
}
