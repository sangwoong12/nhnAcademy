package com.nhnacademy.nhnmart.item;

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
            return null;
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
    public Food getByFoodName(String foodName){
        Optional<Food> food = foodMap.keySet().stream()
                .filter(f -> f.getName().equals(foodName)).findAny();
        if(food.isEmpty()){
            throw new RuntimeException("판매하지 않는 음식입니다.");
        }
        return food.get();
    }
}
