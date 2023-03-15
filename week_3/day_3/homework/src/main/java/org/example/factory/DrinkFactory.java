package org.example.factory;

import org.example.exception.NotFoundEssence;
import org.example.item.Americano;
import org.example.item.CafeLatte;
import org.example.item.Choco;
import org.example.item.Essence;
import org.example.item.MochaCino;
import org.example.item.PeachTea;
import org.example.type.DrinkName;

/**
 * drink Factory.
 */
public class DrinkFactory {

    private DrinkFactory() {}

    /**
     * DrinkName 에 따라 클래스 주입.
     *
     * @param drinkName : 음료 이름
     * @return : 음료 클래스
     */
    public static Essence create(DrinkName drinkName) {
        switch (drinkName) {
            case CHOCE:
                return new Choco();
            case MOCHA_CINO:
                return new MochaCino();
            case PEACH_TEA:
                return new PeachTea();
            case CAFE_LATTE:
                return new CafeLatte();
            case AMERICANO:
                return new Americano();
            default:
                throw new NotFoundEssence();
        }
    }
}
