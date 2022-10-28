package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository cRepo;

    public Customer add(Customer customer) {
        cRepo.save(customer);
        return customer;
    }

    public List<Customer> list() {
        return cRepo.findAll();
    }


}
