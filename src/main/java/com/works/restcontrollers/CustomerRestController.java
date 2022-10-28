package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerRestController {

    final CustomerService service;

    @PostMapping("/add")
    public ResponseEntity<Customer> add(Customer customer) {
        return service.add(customer);
    }

    @GetMapping("/list")
    public List<Customer> list() {
        return service.list();
    }

}
