package org.example;


public class Customer {
    // 고객의 구매 목록
    private final BuyList buyList;
    // 고객의 장바구니
    private Basket basket;
    int money = 20_000;
    public Customer(BuyList buyList) {
        this.buyList = buyList;
    }

    // 장바구니를 챙김
    public void bring(Basket basket) {
        this.basket = basket;
    }
    public void pickFoods(FoodStand foodStand){
        for (BuyList.Item item : buyList.getItems()) {
            for(int i = 0; i < item.getAmount(); i++){
                Food food = foodStand.getFood(item.getName());
                basket.add(food);
            }
        }
    }
    public void payTox(Counter counter){
        this.money = counter.payment(basket, money);
    }
}
