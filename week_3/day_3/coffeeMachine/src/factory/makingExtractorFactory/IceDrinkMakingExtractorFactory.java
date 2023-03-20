package factory.makingExtractorFactory;

import machine.IceDrinkMakingExtractor;
import machine.MakingExtractor;
import object.Drink;

public class IceDrinkMakingExtractorFactory implements MakingExtractorFactory {
    @Override
    public MakingExtractor create(Drink drink) {
        return new IceDrinkMakingExtractor(drink);
    }
}
