package com.nhnacademy.nhnmart.item;

import java.util.HashMap;
import java.util.Map;

public class Baskets {
    //user id 로 user의 buylist를 가지고
    private final Map<String,BuyList> baskets = new HashMap<>();

    public Baskets(){

    }

    public Map<String, BuyList> getBaskets() {
        return baskets;
    }
    //TODO : 유저가 장바구니에 더 넣을 경우 전 데이터를 날리지 말고 추가되는 형식으로 구현
    public void add(BuyList buyList,String name){
        if(baskets.containsKey(name)){
            BuyList buyList1 = baskets.get(name);
            Map<Food, Integer> originBuyList = buyList1.getBuyList();
            Map<Food, Integer> newBuyList = buyList.getBuyList();
            originBuyList.replaceAll((f, v) -> originBuyList.get(f) + newBuyList.get(f));
        }else {
            baskets.put(name,buyList);
        }
    }
}
