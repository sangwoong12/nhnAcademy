package org.example.type;

/**
 * 음류 이름.
 */
public enum DrinkName {
    AMERICANO("아메리카노"), CAFE_LATTE("카페라떼"), CHOCE("초코"), MOCHA_CINO("모카치노"), PEACH_TEA("복숭아티");
    String koreaName;

    DrinkName(String koreaName) {
        this.koreaName = koreaName;
    }

    public String getKoreaName() {
        return koreaName;
    }
}
