package org.example.machine;

import org.example.item.Essence;
import org.example.item.Ice;

/**
 * 아이스 추출기.
 */
public class IceDrinkMakingExtractor implements MakingExtractor {
    String cub = "플라스틱 컵";

    @Override
    public String getCub() {
        return cub;
    }

    @Override
    public boolean getIce() {
        return true;
    }

    @Override
    public boolean getDrink(Essence essence) {
        if (!(essence instanceof  Ice)) {
            System.out.println("해당음류는 아이스를 지원하지 않습니다.");
            return false;
        }
        return true;
    }
}
