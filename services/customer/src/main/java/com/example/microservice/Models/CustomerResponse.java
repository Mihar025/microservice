package com.example.microservice.Models;

import com.example.microservice.Models.Address;

public record CustomerResponse(
        String id,
        String firstname,

        String lastname,

        String email,


        Address address
) {


}
