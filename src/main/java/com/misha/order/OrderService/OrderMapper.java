package com.misha.order.OrderService;

import com.misha.order.Controller.OrderRequest;
import com.misha.order.Models.Order;
import org.springframework.stereotype.Service;

@Service

public class OrderMapper {


    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAMount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }
}