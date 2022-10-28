package com.works;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import com.works.services.CustomerService;
import com.works.utils.Action;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerTests {

    private RestTemplate restTemplate;
    @BeforeAll
    public void beforeAll() {
        restTemplate = new RestTemplate();
    }

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    @Order(1)
    public void dbControl() {
        System.out.println("Call-1");
        Assertions.assertNotNull(customerRepository);
    }

    @Test
    @Order(2)
    public void dbInsertControl() {
        System.out.println("Call-2");
        Customer customer = new Customer();
        customer.setEmail("ali@mail.com");
        customer.setName("Ali Bilirim");
        customerRepository.save(customer);
        Assertions.assertEquals(customer.getCid(), 1);
    }

    @Test
    @Order(3)
    public void customerServiceControl() {
        String url = "http://localhost:8090/customer/list";
        List<Customer> ls = restTemplate.getForObject(url, List.class);
        Assertions.assertNotEquals(ls.size(), 0 );
    }


}
