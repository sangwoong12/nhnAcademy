package org.example.factory;

import org.example.exception.NotFoundExtractor;
import org.example.machine.HotDrinkMakingExtractor;
import org.example.machine.IceDrinkMakingExtractor;
import org.example.machine.MakingExtractor;
import org.example.type.DrinkStatus;

/**
 * ice or hot machine Factory.
 */
public class MakingMachineFactory {

    private MakingMachineFactory() {}

    /**
     * drinkType 에 따른 클래스 주입.
     *
     * @param drinkStatus : 음류 타입 (ice or hot)
     * @return : 머신 클래스
     */
    public static MakingExtractor create(DrinkStatus drinkStatus) {
        switch (drinkStatus) {
            case HOT:
                return new HotDrinkMakingExtractor();
            case ICE:
                return new IceDrinkMakingExtractor();
            default:
                throw new NotFoundExtractor();
        }
    }
}
