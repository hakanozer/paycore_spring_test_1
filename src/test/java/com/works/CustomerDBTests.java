package com.works;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerDBTests {

    @Autowired
    CustomerRepository customerRepository;

    @BeforeAll
    public void beforeAll() {
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.setName("User Bilirim-"+i);
            customer.setEmail("serkan@mail.com-"+i);
            customerRepository.save(customer);
        }
    }

    @Test
    @DisplayName("Customer Add Test")
    @Order(1)
    public void addTest() {
        Customer customer = new Customer();
        customer.setName("Serkan Bilirim");
        customer.setEmail("serkan@mail.com");
        customerRepository.save(customer);
        Assertions.assertEquals(customer.getCid(), 11);
    }

    @Test
    @DisplayName("Customer Delete Test")
    @Order(2)
    public void deleteTest() {
        try {
            Customer customer = new Customer();
            customer.setName("Serkan Bilirim");
            customer.setEmail("serkan@mail.com");
            customerRepository.save(customer);

            customerRepository.deleteById(2L);
            Assertions.assertTrue(true);
        }catch (Exception ex) {
            ex.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    @DisplayName("Customer List Test")
    @Order(3)
    public void listTest() {
        List<Customer> list = customerRepository.findAll();
        Assertions.assertEquals(list.size(), 10);
    }


    @Test
    @DisplayName("Customer Email Find Test")
    @Order(4)
    public void emailFindTest() {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase("serkan@mail.com-1");
        System.out.println(optionalCustomer.get());
        Assertions.assertNotNull(optionalCustomer.get());
    }

    @Test
    @DisplayName("Customer Cid Test")
    @Order(5)
    public void customerCidTest() {
        List<Customer> list = customerRepository.fncFindCid(3);
        System.out.println(list);
        Assertions.assertEquals(list.size(), 7);
    }

}
