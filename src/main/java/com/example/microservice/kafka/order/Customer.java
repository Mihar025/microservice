package com.example.microservice.kafka.order;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record Customer(

        String id,

        String firstname,

        String lastname,
        String email,

        List<Product> products


) {
}
