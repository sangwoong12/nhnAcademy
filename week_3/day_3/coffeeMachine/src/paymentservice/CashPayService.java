package paymentservice;

/**
 * 현금 결제 시스템.
 */
public class CashPayService extends PaymentService {

    @Override
    void print() {
        System.out.println("현금이 충분한지 확인중입니다.");
    }
}
