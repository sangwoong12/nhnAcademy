package factory.pay_service_fatory;

import paymentservice.CreditCardPayService;
import paymentservice.PaymentService;

public class CreditCardPayServiceFactory implements PayServiceFactory{
    @Override
    public PaymentService create() {
        return new CreditCardPayService();
    }
}
