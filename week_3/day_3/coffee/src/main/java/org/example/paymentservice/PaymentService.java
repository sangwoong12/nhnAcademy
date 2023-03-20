package org.example.paymentservice;

/**
 * 결제 시스템 인터페이스.
 * 결제 가능 여부를 확인한다.
 */
public abstract class PaymentService {

    public boolean enoughPayMoney(int money, int drinkPrice){
        print();
        if (money > drinkPrice) {
            System.out.println("결제 완료되었습니다.");
            return true;
        }
        System.out.println("잔액 부족 결제가 취소되었습니다.");
        return false;
    }
    abstract void print();
}
