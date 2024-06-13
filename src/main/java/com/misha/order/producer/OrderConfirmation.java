package com.misha.order.producer;

import com.misha.order.Models.PaymentMethod;
import com.misha.order.OrderService.PurchaseResponse;
import com.misha.order.customer.CustomerResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products


) {
}
