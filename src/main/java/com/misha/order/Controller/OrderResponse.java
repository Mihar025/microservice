package com.misha.order.Controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.misha.order.Models.PaymentMethod;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {

}