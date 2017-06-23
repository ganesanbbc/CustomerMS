package com.cts.customer.template;

import com.cts.customer.CustomerApplication;
import com.cts.customer.controller.CustomerController;
import com.cts.customer.service.CustomerService;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CustomerApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerControllerTest {


    public static final String SOME_NAME = "SOME_NAME";

    private MockMvc mockMVC;

    @Mock
    private CustomerService personService;

    @Autowired
    private WebApplicationContext wac;

    @InjectMocks
    private CustomerController controller;

    @Before
    public void setup() {
        this.mockMVC = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void shouldGetCustomers() throws Exception{
        mockMVC.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));

    }

   @Test
    public void shouldGetAddCustomerPage() throws Exception{
        mockMVC.perform(get("/createCustomer")).andExpect(status().isOk()).andExpect(view().name("addCustomer"));

    }

    @Test
    public void shouldGetAddCustomerServicePage() throws Exception{
        mockMVC.perform(post("/createCustomer")).andExpect(status().isOk()).andExpect(view().name("addService"));
    }

}
