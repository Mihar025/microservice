package com.misha.order.OrderService;

import com.misha.order.Controller.OrderRequest;
import com.misha.order.customer.CustomerClient;
import com.misha.order.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public Integer createOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exist with the provided Id"));



        //persist order

        //persist order lines

        // start payment process

        // send the order confirmation to our --> notification-ms(kafka)


    return null;
    }
}
