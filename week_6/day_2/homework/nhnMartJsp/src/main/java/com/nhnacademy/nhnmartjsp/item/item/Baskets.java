package com.nhnacademy.nhnmartjsp.item.item;

import java.util.HashMap;
import java.util.Map;

public class Baskets {
    //user id 로 user의 buylist를 가지고
    private final Map<String,BuyList> baskets = new HashMap<>();

    public Baskets(){

    }
    class Basket{
        Map<Food,Integer> foodList = new HashMap<>();


    }
}
