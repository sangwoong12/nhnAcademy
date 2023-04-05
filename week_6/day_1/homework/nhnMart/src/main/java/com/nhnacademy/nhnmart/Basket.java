package com.nhnacademy.nhnmart;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final ArrayList<Food> foods = new ArrayList<>();

    public void add(Food food) {
        foods.add(food);
    }
    public List<Food> getFoods(){
        return foods;
    }
}
