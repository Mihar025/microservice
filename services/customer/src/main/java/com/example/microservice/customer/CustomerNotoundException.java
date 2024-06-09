package com.example.microservice.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerNotoundException extends RuntimeException {

    private final String msg;

}
