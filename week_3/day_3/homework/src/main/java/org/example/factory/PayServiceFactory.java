package org.example.factory;

import org.example.exception.NotFoundPayType;
import org.example.paymentservice.CashPayService;
import org.example.paymentservice.CreditCardPayService;
import org.example.paymentservice.OnlinePayService;
import org.example.paymentservice.PaymentService;
import org.example.type.PayType;

/**
 * payService factory.
 */
public class PayServiceFactory {

    private PayServiceFactory() {}

    /**
     * payType 에 따른 클래스 주입.
     *
     * @param payType : 결제 종류
     * @return : 결제 서비스 클래스
     */
    public static PaymentService create(PayType payType) {
        switch (payType) {
            case CASH:
                return new CashPayService();
            case ONLINE:
                return new OnlinePayService();
            case CREDIT_CARD:
                return new CreditCardPayService();
            default:
                throw new NotFoundPayType();
        }
    }
}
