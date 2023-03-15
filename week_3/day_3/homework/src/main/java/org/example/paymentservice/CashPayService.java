package org.example.paymentservice;

/**
 * 현금 결제 시스템.
 */
public class CashPayService implements PaymentService {

    @Override
    public boolean enoughPayMoney(int money, int drinkPrice) {
        System.out.println("현금이 충분한지 확인중입니다.");
        if (money > drinkPrice) {
            System.out.println("결제 완료되었습니다.");
            return true;
        }
        System.out.println("잔액 부족 결제가 취소되었습니다.");
        return false;
    }
}
