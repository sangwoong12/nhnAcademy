package com.nhnacademy.nhnmart.item;

import java.util.HashMap;
import java.util.Map;

/**
 * FoodStand 가 여러개 존재할수 없기때문에 싱글턴으로 구현
 */
public class FoodStand {
    static FoodStand foodStand;
    private Map<Food,Integer> foodList = new HashMap<>();

    private FoodStand(){}

    public static FoodStand getFoodStand(){
        if(foodStand == null){
            foodStand = new FoodStand();
        }
        return foodStand;
    }
    //init이 다시 호출될경우 기존의 데이터를 날리고 다시 추가하는 방식으로 처리
    public static void setInit(){
        foodStand.foodList = new HashMap<>();
    }
    public void add(Food food, int count){
        foodList.put(food,count);
    }
    public Map<Food,Integer> getFoodList(){
        return foodList;
    }
}
