package com.misha.order.OrderService;

import com.misha.order.Controller.OrderRequest;
import com.misha.order.Controller.PurchaseRequest;
import com.misha.order.customer.CustomerClient;
import com.misha.order.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exist with the provided Id"));

    this.productClient.purchaseProducts(request.products());

    var order = this.orderRepository.save(orderMapper.toOrder(request));

    for(PurchaseRequest purchaseRequest : request.products()){
        orderLineService.saveOrderLine(
                new OrderLineRequest(
                        null,
                        order.getId(),
                        purchaseRequest.product_id(),
                        purchaseRequest.quantity()
                )
        );
    }




    return null;
    }
}
