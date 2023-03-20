package org.example;

import java.util.concurrent.Semaphore;

import org.example.machine.MakingExtractor;
import org.example.object.Drink;
import org.example.paymentservice.PaymentService;

/**
 * 음류를 만들어 주는 커피기계.
 * 음류, 추출기, 결제 시스템을 주입받아 커피를 만든다.
 * 싱글턴 패턴을 사용한다.
 */
public class CoffeeMachine {
    private static CoffeeMachine coffeeMachine;
    private Drink drink;

    private MakingExtractor extractor;
    private PaymentService payService;
    private int inputMoney;

    private static Semaphore useMachine;

    private CoffeeMachine() {

    }

    /**
     * 싱글턴 메서드.
     *
     * @return : CoffeeMachine
     */
    public static CoffeeMachine getInstance() {
        if (coffeeMachine == null) {
            coffeeMachine = new CoffeeMachine();
            useMachine = new Semaphore(1);
        }
        return coffeeMachine;
    }

    /**
     * 필요한 것을 주입 받아 바로 음류를 만드는 메서드.
     *
     * @param extractor : 추출기
     * @param service : 결제 서비스
     * @param drink : 음류
     * @param money : 결제가능 금액
     */
    public void getDrink(MakingExtractor extractor, PaymentService service, Drink drink, int money) throws InterruptedException {
        useMachine.acquire();

        this.payService = service;
        this.extractor = extractor;
        this.inputMoney = money;
        this.drink = drink;

        makeDrink();

        useMachine.release();

    }

    private void makeDrink() {
        if(paymentAvailability()){
            extractor.cook(drink);
        }else{
            System.out.println("구매 실패");
        }
    }

    private boolean paymentAvailability() {
        return payService.enoughPayMoney(inputMoney, drink.getPrice());
    }
}
