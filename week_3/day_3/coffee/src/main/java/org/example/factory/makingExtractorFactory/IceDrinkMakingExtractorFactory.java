package org.example.factory.makingExtractorFactory;

import org.example.machine.IceDrinkMakingExtractor;
import org.example.machine.MakingExtractor;
import org.example.object.Drink;

public class IceDrinkMakingExtractorFactory implements MakingExtractorFactory {
    @Override
    public MakingExtractor create(Drink drink) {
        return new IceDrinkMakingExtractor(drink);
    }
}
