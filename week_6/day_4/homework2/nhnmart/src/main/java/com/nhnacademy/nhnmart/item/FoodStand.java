package com.nhnacademy.nhnmart.item;

import com.nhnacademy.nhnmart.exception.AmountException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class FoodStand {
    private Map<Food,Integer> foodMap = new HashMap<>();

    public void add(Food food,int amount){
        foodMap.put(food,amount);
    }
    public Food getFoods(Food food,int amount){
        if(!enoughFoods(food,amount)) {
            throw new AmountException();
        }
        foodMap.put(food, foodMap.get(food) - amount);
        return food;
    }
    public boolean enoughFoods(Food food,int amount){
        return foodMap.get(food) >= amount;
    }
    public Map<Food,Integer> getFoodMap(){
        return foodMap;
    }
}
