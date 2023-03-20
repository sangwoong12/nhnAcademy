package org.example.type;

/**
 * 음류 상태.
 */
public enum DrinkStatus {
    HOT("핫"), ICE("아이스");
    String koreaName;

    DrinkStatus(String koreaName) {
        this.koreaName = koreaName;
    }

    public String getKoreaName() {
        return koreaName;
    }
}
