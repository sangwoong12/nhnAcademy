package factory.DrinkFactory;

import object.Drink;
import type.DrinkName;
import type.EssenceType;
import type.LiquidType;
import type.ToppingType;

public class DrinkFactory {
    public Drink create(DrinkName drinkName, EssenceType essenceType, LiquidType liquidType, ToppingType toppingType, int price) {
        return new Drink(drinkName,essenceType,liquidType,toppingType,price);
    }
}
