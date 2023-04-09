package com.nhnacademy.nhnmart.item;

import java.util.HashMap;
import java.util.Map;

public class BuyListRepository {
    private final Map<String,BuyList> baskets = new HashMap<>();

    public void add(String id,BuyList buyList){
        Map<Food, Integer> originBuyList = getById(id).getBuyList();
        Map<Food, Integer> newBuyList = buyList.getBuyList();
        BuyList combinedBuylist = new BuyList();
        Map<Food, Integer> combinedBuyList = combinedBuylist.getBuyList();

        // originBuyList의 모든 엔트리를 combinedBuyList에 추가
        combinedBuyList.putAll(originBuyList);

        // newBuyList의 엔트리를 combinedBuyList에 추가하면서 겹치는 경우 value 값을 합산
        for (Map.Entry<Food, Integer> entry : newBuyList.entrySet()) {
            Food food = entry.getKey();
            Integer count = entry.getValue();
            if (combinedBuyList.containsKey(food)) {
                // 기존에 이미 등록된 음식인 경우, 기존 값과 합산하여 put
                count += combinedBuyList.get(food);
            }
            combinedBuyList.put(food, count);
        }
        baskets.put(id,combinedBuylist);
    }
    public BuyList getById(String id){
        return baskets.containsKey(id) ? baskets.get(id) : new BuyList();
    }
}
