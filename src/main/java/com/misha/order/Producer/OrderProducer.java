package com.misha.order.Producer;

import com.misha.order.config.KafkaOrderTopicConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {


    public void sendOrderConfirmation(OrderConfirmation orderConfirmation){
        log.info("Sending order confirmation");  

    }
}
