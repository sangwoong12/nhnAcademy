import exception.NotFoundDrinkType;
import exception.NotFoundPayType;
import factory.makingExtractorFactory.HotDrinkMakingExtractorFactory;
import factory.makingExtractorFactory.IceDrinkMakingExtractorFactory;
import factory.makingExtractorFactory.MakingExtractorFactory;
import factory.pay_service_fatory.CashPayServiceFactory;
import factory.pay_service_fatory.CreditCardPayServiceFactory;
import factory.pay_service_fatory.OnlinePayServiceFactory;
import factory.pay_service_fatory.PayServiceFactory;
import object.Drink;
import type.DrinkName;
import type.DrinkStatus;
import type.PayType;

public class CoffeeMachineTest {


    static DrinkName drinkName;
    static DrinkStatus drinkStatus;
    static PayType payType;
    static Drink drink;

    static int money;

    /**
     * 테스트.
     *
     * @param args : 사용안함
     */
    public static void main(String[] args) throws InterruptedException {
        CoffeeMachine coffeeMachine = CoffeeMachine.getInstance();
        // 주문
        drinkName = DrinkName.AMERICANO;
        drinkStatus = DrinkStatus.ICE;
        payType = PayType.CASH;
        money = 10000;

        MakingExtractorFactory makingExtractorFactory;
        PayServiceFactory payServiceFactory;

        // Drink 얻기
        if (drinkStatus.equals(DrinkStatus.ICE)){
            drink = MakeIceDrink.getDrink(drinkName);
        } else if(drinkStatus.equals(DrinkStatus.HOT)) {
            drink = MakeHotDrink.getDrink(drinkName);
        } else {
            throw new NotFoundDrinkType();
        }

        // DrinkExtractor 상태에 따라 선택
        if (drinkStatus.equals(DrinkStatus.ICE)){
            makingExtractorFactory = new IceDrinkMakingExtractorFactory();
        } else if (drinkStatus.equals(DrinkStatus.HOT)){
            makingExtractorFactory = new HotDrinkMakingExtractorFactory();
        } else {
            throw new NotFoundDrinkType();
        }

        // PayService 상태에 따라 선택
        if (payType.equals(PayType.CASH)) {
            payServiceFactory = new CashPayServiceFactory();
        } else if (payType.equals(PayType.ONLINE)) {
            payServiceFactory = new OnlinePayServiceFactory();
        } else if (payType.equals(PayType.CREDIT_CARD)) {
            payServiceFactory = new CreditCardPayServiceFactory();
        } else {
            throw new NotFoundPayType();
        }
        coffeeMachine.getDrink(makingExtractorFactory.create(drink),payServiceFactory.create(),drink,money);

    }
}
