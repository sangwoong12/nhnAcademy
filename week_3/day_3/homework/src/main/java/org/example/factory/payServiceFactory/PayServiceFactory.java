package org.example.factory.payServiceFactory;

import org.example.paymentservice.PaymentService;

public interface PayServiceFactory {
    PaymentService create();
}
