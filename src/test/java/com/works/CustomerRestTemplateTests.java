package com.works;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.entities.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerRestTemplateTests {

    @Autowired private ObjectMapper objectMapper;

    RestTemplate restTemplate;
    @BeforeAll
    public void setup() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void addTest() throws JsonProcessingException {
        String url = "http://localhost:8090/customer/add";
        Customer customer = new Customer();
        customer.setName("Mehmet Bil");
        customer.setEmail("mehmet@mail.com" + (int) (Math.random() *  100));

        String stCustomer = objectMapper.writeValueAsString(customer);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(stCustomer, httpHeaders);

        ResponseEntity<Customer> res = restTemplate.postForEntity(url, httpEntity, Customer.class);
        Assertions.assertAll(
                () -> Assertions.assertEquals(res.getStatusCodeValue(), 200),
                () -> Assertions.assertNotNull(res.getBody())
        );
    }

}
