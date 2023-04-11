package com.nhnacademy.nhnmart.item;


import java.util.*;

public class BuyList {
    private final Map<Food,Integer> buyList = new HashMap<>();

    public void add(Food food,int count){
        buyList.put(food, buyList.containsKey(food) ? buyList.get(food) + count : count);
    }
    public Map<Food,Integer> getBuyList(){
        return buyList;
    }
    public int getTotalPrice(List<Food> foodNames){
        return foodNames.stream().mapToInt(foodName -> buyList.get(foodName) * foodName.getPrice()).sum();
    }
    public void removeFood(List<Food> foodNames){
        foodNames.stream().forEach(buyList::remove);
    }

}
