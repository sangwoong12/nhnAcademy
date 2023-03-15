package org.example;

import org.example.type.DrinkName;
import org.example.type.DrinkStatus;
import org.example.type.PayType;

/**
 * 테스트 클래스.
 */
public class Main {

    /**
     * 테스트.
     *
     * @param args : 사용안함
     */
    public static void main(String[] args) {
        CaffeMachine caffeMachine = CaffeMachine.getInstance();
        caffeMachine.getDrink(DrinkName.MOCHA_CINO, DrinkStatus.ICE, PayType.ONLINE, 10000);
        caffeMachine.getDrink(DrinkName.AMERICANO, DrinkStatus.ICE, PayType.CREDIT_CARD, 10000);

    }
}