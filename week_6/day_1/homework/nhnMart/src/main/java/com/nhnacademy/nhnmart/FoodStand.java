package com.nhnacademy.nhnmart;

import java.util.ArrayList;

public class FoodStand {
    private final ArrayList<Food> foods = new ArrayList<>();

    public void add(Food onion1) {
        foods.add(onion1);
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }
    public Food getFood(String name){
        for (Food food : foods) {
            if(food.getName().equals(name)){
                foods.remove(food);
                return food;
            }
        }
        throw new NotFoundFoodException();
    }
}
