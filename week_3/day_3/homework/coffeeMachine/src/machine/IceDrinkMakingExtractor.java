package machine;

import object.Drink;

/**
 * 아이스 음류 추출기.
 */
public class IceDrinkMakingExtractor extends MakingExtractor {
    Drink drink;

    public IceDrinkMakingExtractor(Drink drink) {
        this.drink = drink;
    }

    @Override
    void getIce() {
        System.out.println("얼음이 나옵니다.");
    }

    @Override
    void getCup() {
        System.out.println("컵 나오는 곳에서 플라스틱 컵이 나옵니다.");
    }

    @Override
    void getDrink() {
        System.out.println("아이스 " + drink.getDrinkName().getKoreaName() + "가 완성되었습니다.");
    }
}
