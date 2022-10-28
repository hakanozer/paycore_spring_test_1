package com.works;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import com.works.restcontrollers.CustomerRestController;
import com.works.services.CustomerService;
import com.works.utils.Action;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class CustomerTests {

    private RestTemplate restTemplate;
    @BeforeAll
    public void beforeAll() {
        restTemplate = new RestTemplate();
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Mock
    private CustomerService service;

    @InjectMocks
    private CustomerRestController customerRestController;


    @Test
    @Order(1)
    public void dbControl() {
        System.out.println("Call-1");
        Assertions.assertNotNull(customerRepository);
    }

    @Test
    @Order(2)
    @DisplayName("db Insert Control Name")
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

    int i = 0;
    @Test
    @RepeatedTest(5)
    public void repeatedFnc() {
        i++;
        System.out.println("i: " + i);
    }

    @ParameterizedTest
    @ValueSource( strings = {"İstanbul", "İzmir", "Ankara"})
    public void values( String city ) {
        System.out.println( city );
    }

    @ParameterizedTest(name = "1st={0}, 2nd={1}, 3xd={2}")
    @CsvSource( value = { "10, 12, 55", "30, 44, 67" })
    public void csvSource ( int a, int b, int c ) {
        int sm = a + b + c;
        System.out.println("Sum : " + sm);
    }


    @Test
    public void failTest() {
        try {
            int a = 1 / 0;
            System.out.println("a : " + a);
        }catch (ArithmeticException ex) {
          //Assertions.assertThrows( ArithmeticException.class, () -> ex.printStackTrace() );
        }
    }

    // Mock Test
    @Test
    public void mocktest() {
        Customer customer = new Customer();
        customer.setName("Serkan");
        customer.setEmail("Bilirim");
        Optional<Customer> expected = Optional.of(customer);

        ResponseEntity<Customer> reponse = customerRestController.add(customer);
        when( service.add( any() ) ).thenReturn(reponse);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(reponse.getBody()),
                () -> Assertions.assertEquals(reponse.getStatusCode(), HttpStatus.OK),
                () -> Assertions.assertEquals( reponse.getBody().getCid(), 1 )
        );

    }


}
