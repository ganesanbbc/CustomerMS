package com.cts.customer.template;

import com.cts.customer.CustomerApplication;
import com.cts.customer.utils.CustomerEndPoints;
import com.cts.customer.controller.CustomerController;
import com.cts.customer.service.CustomerService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Ignore
    @Test
    public void thatGotLoadPageWhenRequestMainPage() throws Exception {

        MockHttpServletRequestBuilder action = MockMvcRequestBuilders.
                get(CustomerEndPoints.INDEX_URL)
                .accept(APPLICATION_JSON);
        ResultMatcher expectedResult = status().isOk();
        mockMVC.perform(action)
                .andExpect(expectedResult);

    }

    @Ignore
    @Test
    public void thatGotLoadPageWhenRequestDefaultPage() throws Exception {

        MockHttpServletRequestBuilder action = MockMvcRequestBuilders
                .get("/")
                .accept(APPLICATION_JSON);

        ResultMatcher expectedResult = status().isOk();
        mockMVC.perform(action)
                .andExpect(expectedResult);

    }

    @Ignore
    @Test
     public void thatResponseContainsCompanyListWhenRequestListofCompany() throws Exception {

        MockHttpServletRequestBuilder action = MockMvcRequestBuilders
                .get(CustomerEndPoints.GET_CUSTOMERS_URL)
                .accept(APPLICATION_JSON);

//        CustomerDetails person = new CustomerDetails(SOME_NAME);
//        List<CustomerDetails> personList = new ArrayList<>();
//        personList.add(person);
//        when(personService.getPersons()).thenReturn(personList);
//
//        ResultMatcher expectedResult1 = status().isOk();
//        ResultMatcher expectedResult2 = jsonPath("$", hasSize(1));
//        ResultMatcher expectedResult3 = jsonPath("$[0].name").exists();
//        ResultMatcher expectedResult4 = jsonPath("$[0].name").value(SOME_NAME);
//
//        mockMVC.perform(action)
//                .andExpect(expectedResult1)
//                .andExpect(expectedResult2)
//                .andExpect(expectedResult3)
//                .andExpect(expectedResult4);
    }

    @Test
    public void shouldGetCustomers() throws Exception{
        mockMVC.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));

    }

   @Test
    public void shouldGetAddCustomerPage() throws Exception{
        mockMVC.perform(get("/createCustomer")).andExpect(status().isOk()).andExpect(view().name("addCustomer"));

    }

}
