package org.example.machine;

import org.example.object.Drink;

/**
 * 뜨거운 음류 추출기.
 */
public class HotDrinkMakingExtractor extends MakingExtractor {
    Drink drink;
    public HotDrinkMakingExtractor(Drink drink) {
        this.drink = drink;
    }

    @Override
    void getIce() {
    }

    @Override
    void getCup() {
        System.out.println("컵 나오는 곳에서 종이 컵이 나옵니다.");
    }

    @Override
    void getDrink() {
        System.out.println("뜨거운 " + drink.getDrinkName().getKoreaName() + "가 완성되었습니다.");
    }
}
