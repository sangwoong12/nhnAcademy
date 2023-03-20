package factory.pay_service_fatory;

import paymentservice.PaymentService;

public interface PayServiceFactory {
    PaymentService create();
}
