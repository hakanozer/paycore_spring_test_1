package com.works;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.entities.Customer;
import com.works.restcontrollers.CustomerRestController;
import com.works.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {CustomerRestController.class})
public class CustomerRestControllerTests {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void customerAdd() throws Exception {
        String url = "/customer/add";
        Customer customer = new Customer();
        customer.setEmail("ahmet@mail.com");
        customer.setName("Ahmet Bilsin");
        customer.setCid(1L);
        String stCustomer = objectMapper.writeValueAsString(customer);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(stCustomer)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
                //.andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
        ;
        String content = mvcResult.getRequest().getContentAsString();
        Customer cCustomer = objectMapper.readValue(content, Customer.class);
        Assertions.assertEquals(cCustomer.getCid(), 1);

    }

    @Test
    public void delete() throws Exception {
        String url = "/customer/delete/1";
        mockMvc.perform(
                MockMvcRequestBuilders.delete(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

}
