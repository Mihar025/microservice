package com.misha.order.payment;

import com.misha.order.Models.PaymentMethod;
import com.misha.order.customer.CustomerResponse;
import java.math.BigDecimal;
public record PaymentRequest(

        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer



) {
}
