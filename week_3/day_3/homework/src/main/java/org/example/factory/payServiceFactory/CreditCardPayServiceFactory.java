package org.example.factory.payServiceFactory;

import org.example.paymentservice.CreditCardPayService;
import org.example.paymentservice.PaymentService;

public class CreditCardPayServiceFactory implements PayServiceFactory{
    @Override
    public PaymentService create() {
        return new CreditCardPayService();
    }
}
