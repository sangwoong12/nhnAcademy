package com.nhnacademy.nhnmart.item;

import com.nhnacademy.nhnmart.NotFoundFoodException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodStand {
    private final Map<Food,Integer> foodStand = new HashMap<>();

    public void add(Food food, int count){
        foodStand.put(food,count);
    }
    public Map<Food,Integer> getFoodStand(){
        return foodStand;
    }
}
