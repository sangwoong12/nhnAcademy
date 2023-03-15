package org.example.item;

/**
 * 카페라떼.
 */
public class CafeLatte extends Essence implements Ice, Hot {
    public CafeLatte() {
        this.essenceName = "CafeLatte";
        this.drinkPrice = 2000;
    }
}
