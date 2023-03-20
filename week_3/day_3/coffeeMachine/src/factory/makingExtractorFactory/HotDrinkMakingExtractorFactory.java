package factory.makingExtractorFactory;

import machine.HotDrinkMakingExtractor;
import machine.MakingExtractor;
import object.Drink;

public class HotDrinkMakingExtractorFactory implements MakingExtractorFactory{
    @Override
    public MakingExtractor create(Drink drink) {
        return new HotDrinkMakingExtractor(drink);
    }
}
