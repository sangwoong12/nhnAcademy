package org.example.machine;

import org.example.item.Essence;

/**
 * 추출기 인터페이스.
 * 컵, 얼음여부, 해당 상태가능 여부를 가져올수 있다.
 */
public interface MakingExtractor {
    String getCub();

    boolean getIce();

    boolean getDrink(Essence essence);
}
