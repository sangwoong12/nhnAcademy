package org.example.factory.makingExtractorFactory;

import org.example.machine.HotDrinkMakingExtractor;
import org.example.machine.MakingExtractor;
import org.example.object.Drink;

public class HotDrinkMakingExtractorFactory implements MakingExtractorFactory{
    @Override
    public MakingExtractor create(Drink drink) {
        return new HotDrinkMakingExtractor(drink);
    }
}
