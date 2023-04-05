package com.nhnacademy.nhnmartjsp.item.item;

import java.util.HashMap;
import java.util.Map;

/**
 * FoodStand 가 여러개 존재할수 없기때문에 싱글턴으로 구현
 */
public class FoodStand {
    static FoodStand foodStand;
    private final Map<Food,Integer> foodList = new HashMap<>();

    private FoodStand(){}

    public static FoodStand getFoodStand(){
        if(foodStand == null){
            foodStand = new FoodStand();
        }
        return foodStand;
    }

    public void add(Food food, int count){
        foodList.put(food,count);
    }
    public Map<Food,Integer> getFoodList(){
        return foodList;
    }
}
