package org.example.paymentservice;

/**
 * 결제 시스템 인터페이스.
 * 결제 가능 여부를 확인한다.
 */
public interface PaymentService {

    boolean enoughPayMoney(int money, int drinkPrice);
}
