package com.nhnacademy.nhnmart.item;

import java.util.HashMap;
import java.util.Map;

public class BuyListRepository {
    private final Map<String,BuyList> baskets = new HashMap<>();

    public void add(String id,BuyList buyList){
        if(baskets.containsKey(id)){
            Map<Food, Integer> originBuyList = getById(id).getBuyList();
            Map<Food, Integer> newBuyList = buyList.getBuyList();
            originBuyList.replaceAll((f, v) -> originBuyList.get(f) + newBuyList.get(f));
        }else {
            baskets.put(id,buyList);
        }
    }
    public BuyList getById(String id){
        return baskets.get(id);
    }
}
