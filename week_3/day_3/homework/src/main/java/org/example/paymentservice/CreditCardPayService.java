package org.example.paymentservice;

/**
 * 카드 결제 시스템.
 */
public class CreditCardPayService implements PaymentService {

    @Override
    public boolean enoughPayMoney(int money, int drinkPrice) {
        System.out.println("카드사에 잔액이 충분한 확인중입니다.");
        if (money > drinkPrice) {
            System.out.println("결제 완료되었습니다.");
            return true;
        }
        System.out.println("잔액 부족 결제가 취소되었습니다.");
        return false;
    }
}
