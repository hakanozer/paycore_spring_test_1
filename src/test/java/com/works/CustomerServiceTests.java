package com.works;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class CustomerServiceTests {

   @Autowired
   CustomerService customerService;

   @Test
   public void addTest() {
       Customer customer = new Customer();
       customer.setName("Kaan Bilsin");
       customer.setEmail("kaan@mail.com");
       ResponseEntity<Customer> customerResponseEntity = customerService.add(customer);
       Customer newCustomer = customerResponseEntity.getBody();
       Assertions.assertAll(
               () -> Assertions.assertNotNull(newCustomer),
               () -> Assertions.assertNotEquals(newCustomer.getCid(), null),
               () -> Assertions.assertEquals(customerResponseEntity.getStatusCodeValue(), 200)
       );
   }

   @Test
    public void updateTest() {
       Customer customer = new Customer();
       customer.setName("Zehra Bilirler");
       customer.setEmail("zehra@mail.com");
       customer.setCid(10L);

       ResponseEntity<Customer> customerResponseEntity = customerService.update(customer);
       Customer newCustomer = customerResponseEntity.getBody();
       Assertions.assertEquals(customerResponseEntity.getStatusCodeValue(), 200);

   }


}
