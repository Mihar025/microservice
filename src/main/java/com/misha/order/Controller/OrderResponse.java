package com.misha.order.Controller;

import com.misha.order.Models.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
        ) {

}
