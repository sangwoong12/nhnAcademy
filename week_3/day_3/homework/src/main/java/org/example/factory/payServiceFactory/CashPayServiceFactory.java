package org.example.factory.payServiceFactory;

import org.example.paymentservice.CashPayService;
import org.example.paymentservice.PaymentService;

public class CashPayServiceFactory implements PayServiceFactory {
    @Override
    public PaymentService create() {
        return new CashPayService();
    }
}
