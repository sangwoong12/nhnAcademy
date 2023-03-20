package org.example.factory.makingExtractorFactory;

import org.example.machine.MakingExtractor;
import org.example.object.Drink;

public interface MakingExtractorFactory {
    MakingExtractor create(Drink drink);
}
