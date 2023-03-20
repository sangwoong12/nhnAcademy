import exception.NotFoundDrink;
import factory.DrinkFactory.DrinkFactory;
import object.Drink;
import type.DrinkName;
import type.EssenceType;
import type.LiquidType;
import type.ToppingType;

/**
 * Hot drinkType 에 따른 필요한 데이터를 넣어줌.
 */
public class MakeHotDrink {
    private MakeHotDrink(){

    }
    static DrinkName drinkName;
    static EssenceType essenceType;
    static LiquidType liquidType;
    static ToppingType toppingType;
    static int price;
    static DrinkFactory drinkFactory = new DrinkFactory();
    /**
     * 핫 음류를 만든다.
     *
     * @param name : 음류 이름
     * @return : 핫 음류 클래스
     */
    public static Drink getDrink(DrinkName name) {
        switch (name) {
            case CHOCE:
                drinkName = DrinkName.CHOCE;
                essenceType = EssenceType.CHOCE;
                liquidType = LiquidType.MILK;
                toppingType =  ToppingType.CREAM;
                price = 3000;
                break;
            case AMERICANO:
                drinkName = DrinkName.AMERICANO;
                essenceType = EssenceType.ESPRESSO;
                liquidType = LiquidType.WATER;
                toppingType =  ToppingType.NULL;
                price = 1500;
                break;
            case PEACH_TEA:
                drinkName = DrinkName.PEACH_TEA;
                essenceType = EssenceType.PEACH_ESSENCE;
                liquidType = LiquidType.WATER;
                toppingType =  ToppingType.NULL;
                price = 3500;
                break;
            case CAFE_LATTE:
                drinkName = DrinkName.CAFE_LATTE;
                essenceType = EssenceType.ESPRESSO;
                liquidType = LiquidType.MILK;
                toppingType =  ToppingType.NULL;
                price = 2500;
                break;
            case MOCHA_CINO:
                drinkName = DrinkName.MOCHA_CINO;
                essenceType = EssenceType.ESPRESSO;
                liquidType = LiquidType.MILK;
                toppingType =  ToppingType.CHOCE;
                price = 3500;
                break;
            default:
                throw new NotFoundDrink();
        }
        return drinkFactory.create(drinkName,essenceType,liquidType,toppingType,price);
    }
}
