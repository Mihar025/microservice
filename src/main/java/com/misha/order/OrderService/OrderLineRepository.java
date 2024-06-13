package com.misha.order.OrderService;


import com.misha.order.Models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer > {

    List<OrderLineResponse> findAllByOrderId(Integer orderId);

}
