package com.nhnacademy.nhnmart.item;


import java.util.HashMap;
import java.util.Map;

public class BuyList {
    private final Map<Food,Integer> buyList = new HashMap<>();

    public void add(Food food,int count){
        buyList.put(food, buyList.containsKey(food) ? buyList.get(food) + count : count);
    }
    public Map<Food,Integer> getBuyList(){
        return buyList;
    }
    public int getTotalPrice(){
        return buyList.keySet().stream()
                .mapToInt(food -> food.getPrice() * buyList.get(food)).sum();
    }
}
