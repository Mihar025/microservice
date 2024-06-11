package com.example.microservice.Models;

import com.example.microservice.Models.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest (


     String id,
     @NotNull(message = "Customer first name is required")
     String firstname,
     @NotNull(message = "Customer Last name is required")

     String lastname,
     @NotNull(message = "Customer email is required")
     @Email(message = "Customer email is not valid email address")
     String email,
     @NotNull(message = "Customer first name is required")

     Address address

){

}


