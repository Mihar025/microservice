package com.example.microservice.CustomerService;

import com.example.microservice.Models.Customer;
import com.example.microservice.Models.CustomerRequest;
import com.example.microservice.Models.CustomerResponse;
import com.example.microservice.CustomerRepository.*;
import com.example.microservice.exceptions.CustomerNotoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;


    public String createCustomer(CustomerRequest customerRequest) {
        var customer = repository.save(mapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest customerRequest) {
        var customer = repository.findById(customerRequest.id()).orElseThrow(
                () -> new CustomerNotoundException(
                        format("Cannot update Customer:: No Customer found with the provided ID:: %s", customerRequest.id())
                )
        );
        mergeCustomer(customer, customerRequest);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {
        if(StringUtils.isNotBlank(customerRequest.firstname())){
            customer.setFirstname(customerRequest.firstname());
        }
        if(StringUtils.isNotBlank(customerRequest.lastname())){
            customer.setLastname(customerRequest.lastname());
        }
        if(StringUtils.isNotBlank(customerRequest.email())){
            customer.setEmail(customerRequest.email());
        }
        if(customerRequest.address() != null){
            customer.setAddress(customerRequest.address());
        }
    }

    public List<CustomerResponse> findAllCustomers(){
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList())
                ;
    }

    public Boolean existById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow( () -> new CustomerNotoundException(format(
                        "No customer provided with ID:: %s", customerId
                )));



    }

    public void deleteCustoemr(String customerID) {
        repository.deleteById(customerID);
    }
}
