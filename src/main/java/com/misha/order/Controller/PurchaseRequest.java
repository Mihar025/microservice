package com.misha.order.Controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer product_id,
        @Positive (message = "Quantity is mandatory")
        double quantity
) {
}
