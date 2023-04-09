package com.nhnacademy.nhnmart.item;


import java.util.HashMap;
import java.util.Map;

public class BuyList {
    private final Map<Food,Integer> buyList = new HashMap<>();

    public void add(Food food,int count){
        buyList.put(food,count);
    }
    public Map<Food,Integer> getBuyList(){
        return buyList;
    }

}
