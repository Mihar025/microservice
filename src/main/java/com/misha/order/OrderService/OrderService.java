package com.misha.order.OrderService;

import com.misha.order.Controller.OrderRequest;
import com.misha.order.Controller.OrderResponse;
import com.misha.order.Controller.PurchaseRequest;
import com.misha.order.producer.OrderConfirmation;
import com.misha.order.producer.OrderProducer;
import com.misha.order.customer.CustomerClient;
import com.misha.order.exceptions.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Integer createOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exist with the provided Id"));

    var purchase = this.productClient.purchaseProducts(request.products());

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

    orderProducer.sendOrderConfirmation(
            new OrderConfirmation(
                    request.reference(),
                    request.amount(),
                    request.paymentMethod(),
                    customer,
                    purchase
            )
    );
    return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper:: fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", orderId)));

    }
}