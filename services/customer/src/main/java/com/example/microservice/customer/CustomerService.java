package com.example.microservice.customer;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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
                        String.format("Cannot update Customer:: No Customer found with the provided ID:: %s", customerRequest.id())
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
}
