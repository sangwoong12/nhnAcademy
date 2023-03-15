package org.example.item;

/**
 * 아메리카노.
 */
public class Americano extends Essence implements Ice, Hot {
    public Americano() {
        this.essenceName = "Americano";
        this.drinkPrice = 1000;
    }
}
