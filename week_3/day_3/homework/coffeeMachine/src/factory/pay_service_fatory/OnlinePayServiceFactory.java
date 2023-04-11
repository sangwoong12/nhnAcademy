package factory.pay_service_fatory;

import paymentservice.OnlinePayService;
import paymentservice.PaymentService;

public class OnlinePayServiceFactory implements PayServiceFactory{
    @Override
    public PaymentService create() {
        return new OnlinePayService();
    }
}
