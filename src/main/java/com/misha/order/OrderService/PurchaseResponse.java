package com.misha.order.OrderService;

import java.math.BigDecimal;

public record PurchaseResponse(

        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity,
        String LASTNAme



) {
}
