package org.example.paymentservice;

/**
 * 온라인 결제 서비스.
 */
public class OnlinePayService extends PaymentService {

    @Override
    void print() {
        System.out.println("온라인 페이사에 잔액이 충분한지 확인중입니다.");

    }
}
