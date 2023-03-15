package org.example;

import org.example.factory.DrinkFactory;
import org.example.factory.MakingMachineFactory;
import org.example.factory.PayServiceFactory;
import org.example.item.Essence;
import org.example.machine.MakingExtractor;
import org.example.paymentservice.PaymentService;
import org.example.type.DrinkName;
import org.example.type.DrinkStatus;
import org.example.type.PayType;

/**
 * 음류를 만들어 주는 커피기계.
 * 음류, 추출기, 결제 시스템을 주입받아 커피를 만든다.
 * 싱글턴 패턴을 사용한다.
 */
public class CaffeMachine {
    private static CaffeMachine caffeMachine;
    private Essence essence;
    private MakingExtractor machine;
    private PaymentService payService;
    private int inputMoney;

    private CaffeMachine() {

    }

    /**
     * 싱글턴 메서드.
     *
     * @return : CaffeMachine
     */
    public static CaffeMachine getInstance() {
        if (caffeMachine == null) {
            caffeMachine = new CaffeMachine();
        }
        return caffeMachine;
    }

    /**
     * 필요한 것을 주입 받아 바로 음류를 만드는 메서드.
     *
     * @param drinkName : 음류 이름
     * @param drinkStatus : 음류 상태
     * @param payType : 결제 시스템
     * @param payMoney : 결제 가능한 금액
     */
    public void getDrink(DrinkName drinkName, DrinkStatus drinkStatus,
        PayType payType, int payMoney) {
        this.essence = DrinkFactory.create(drinkName);
        this.machine = MakingMachineFactory.create(drinkStatus);
        this.payService = PayServiceFactory.create(payType);
        this.inputMoney = payMoney;
        makeDrink();
    }

    private void makeDrink() {
        if (machine.getDrink(essence) && paymentAvailability()) {
            System.out.println(machine.getCub() + "이 나옵니다.");
            if (machine.getIce()) {
                System.out.println("얼음이 나옵니다.");
            }
            System.out.println(essence.getEssenceName()  + "가 나옵니다.");

        }
    }

    private boolean paymentAvailability() {
        return payService.enoughPayMoney(inputMoney, essence.getDrinkPrice());
    }
}
