package com.example.microservice.CustomerController;

import com.example.microservice.Models.CustomerRequest;
import com.example.microservice.Models.CustomerResponse;
import com.example.microservice.CustomerService.CustomerService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createNewCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
    ){
        return ResponseEntity.ok(service.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
    ){
        service.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(service.findAllCustomers());
    }

    @GetMapping("/exist/{customer-id}")
    public ResponseEntity<Boolean> existById(@PathVariable("customer-id")  String customerId
    ){
        return ResponseEntity.ok(service.existById(customerId));

    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id")  String customerId
    ){
        return ResponseEntity.ok(service.findById(customerId));

    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> delete(@PathVariable("customer-id") String customerID){
        service.deleteCustoemr(customerID);
        return ResponseEntity.accepted().build();
    }


}
