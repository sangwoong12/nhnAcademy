package factory.makingExtractorFactory;

import machine.MakingExtractor;
import object.Drink;

public interface MakingExtractorFactory {
    MakingExtractor create(Drink drink);
}
