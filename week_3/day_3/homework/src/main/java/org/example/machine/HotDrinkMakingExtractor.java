package org.example.machine;

import org.example.item.Essence;
import org.example.item.Hot;

/**
 * 핫 추출기.
 */
public class HotDrinkMakingExtractor implements MakingExtractor {

    String cub = "종이 ";

    @Override
    public String getCub() {
        return cub;
    }

    @Override
    public boolean getIce() {
        return false;
    }

    @Override
    public boolean getDrink(Essence essence) {
        if (!(essence instanceof Hot)) {
            System.out.println("해당음류는 핫을 지원하지 않습니다.");
            return false;
        }
        return true;
    }
}
