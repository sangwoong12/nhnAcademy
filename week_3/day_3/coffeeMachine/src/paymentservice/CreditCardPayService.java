package paymentservice;

/**
 * 카드 결제 시스템.
 */
public class CreditCardPayService extends PaymentService {

    @Override
    void print() {
        System.out.println("카드사에 잔액이 충분한 확인중입니다.");
    }
}
