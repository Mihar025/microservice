package com.misha.order.customer;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {

}