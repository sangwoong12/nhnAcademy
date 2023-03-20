package org.example.factory.DrinkFactory;

import org.example.object.Drink;
import org.example.type.DrinkName;
import org.example.type.EssenceType;
import org.example.type.LiquidType;
import org.example.type.ToppingType;

public class DrinkFactory {
    public Drink create(DrinkName drinkName, EssenceType essenceType, LiquidType liquidType, ToppingType toppingType, int price) {
        return new Drink(drinkName,essenceType,liquidType,toppingType,price);
    }
}
