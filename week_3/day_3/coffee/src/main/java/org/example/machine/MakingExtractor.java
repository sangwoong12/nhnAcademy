package org.example.machine;

import org.example.object.Drink;
import org.example.type.ToppingType;

/**
 * 음류 추출기 인터페이스.
 */
public abstract class MakingExtractor {
    public void cook(Drink drink){
        getCup();
        getIce();
        if (!drink.getToppingType().equals(ToppingType.NULL)) {
            System.out.println(drink.getToppingType().getKoreaName() + "가 나옵니다.");
        }
        System.out.println(drink.getLiquidType().getKoreaName() + "가 나옵니다.");
        System.out.println(drink.getEssenceType().getKoreaName() + "가 나옵니다.");
        getDrink();
    }
    abstract void getIce();
    abstract void getCup();
    abstract void getDrink();
}
