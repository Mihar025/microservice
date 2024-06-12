package com.misha.order.OrderService;

import com.misha.order.Controller.OrderRequest;
import com.misha.order.customer.CustomerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;


    public Integer createOrder(OrderRequest request) {
        //check customer --> OpenFeign;







        // purchase the product --> product-microservice

        //persist order

        //persist order lines

        // start payment process

        // send the order confirmation to our --> notification-ms(kafka)


    return null;
    }
}
