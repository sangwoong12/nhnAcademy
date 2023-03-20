package org.example.factory.payServiceFactory;

import org.example.paymentservice.OnlinePayService;
import org.example.paymentservice.PaymentService;

public class OnlinePayServiceFactory implements PayServiceFactory{
    @Override
    public PaymentService create() {
        return new OnlinePayService();
    }
}
