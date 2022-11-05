package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository cRepo;

    public ResponseEntity<Customer> add(Customer customer) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        try {
            cRepo.save(customer);
            httpStatus = HttpStatus.OK;
        }catch (Exception ex) {}
        return new ResponseEntity(customer, httpStatus);
    }


    public ResponseEntity<Customer> update( Customer customer ) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Optional<Customer> findCid = cRepo.findById(customer.getCid());
        try {
            if (!findCid.isPresent()) {
                throw new Exception("");
            }
            cRepo.saveAndFlush(customer);
            httpStatus = HttpStatus.OK;
        }catch (Exception ex) {}
        return new ResponseEntity<>(customer, httpStatus);
    }


    public ResponseEntity<Customer> delete( Long cid ) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Optional<Customer> findCid = cRepo.findById(cid);
        try {
            if ( findCid.isPresent() ) {
                cRepo.deleteById(cid);
                httpStatus = HttpStatus.OK;
            }else {
                throw new Exception("");
            }

        }catch (Exception ex) {}
        return new ResponseEntity( findCid.get() , httpStatus);
    }

    public List<Customer> list() {
        return cRepo.findAll();
    }


}
