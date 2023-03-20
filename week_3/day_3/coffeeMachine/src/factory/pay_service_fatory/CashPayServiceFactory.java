package factory.pay_service_fatory;

import paymentservice.CashPayService;
import paymentservice.PaymentService;

public class CashPayServiceFactory implements PayServiceFactory {
    @Override
    public PaymentService create() {
        return new CashPayService();
    }
}
