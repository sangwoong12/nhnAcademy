package org.example.item;

/**
 * 음료 추상클래스.
 * 음류 이름과 음류 가격을 알수있다.
 */
public abstract class Essence {
    protected String essenceName;
    protected int drinkPrice;

    public String getEssenceName() {
        return essenceName;
    }

    public int getDrinkPrice() {
        return drinkPrice;
    }
}
