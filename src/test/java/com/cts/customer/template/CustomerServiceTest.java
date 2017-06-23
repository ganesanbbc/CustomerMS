package com.cts.customer.template;

import com.cts.customer.dao.CustomerDao;
import com.cts.customer.service.CustomerService;
import com.cts.customer.vo.Customer;
import com.cts.customer.vo.ServiceDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by priyadarshini on 6/21/17.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerDao customerDao;

    @Before
    public void  setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateTheCustomer() throws Exception{
        Customer customer = new Customer("Sample");
        Mockito.when(customerDao.createCustomer(customer)).thenReturn(customer);
        Customer actual = customerService.createCustomer(customer);
        assertEquals(customer.getUserName(),actual.getUserName());
    }

    @Test
    public void thatRespondListOfCustomers(){
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer("Test Customer"));
        Mockito.when(customerDao.getAllCustomers()).thenReturn(customerList);

        List<Customer> actualCustomerList =  customerService.getAllCustomers();
        assertThat(actualCustomerList.size(),is(customerList.size()));
    }

    /*@Test
    public void shouldCreateCustomerService() throws Exception{
        ServiceDetails details = new ServiceDetails();
        Mockito.when(customerDao.createServiceDetails(details)).thenReturn(details);
        ServiceDetails actual = customerService.createServiceDetails(details);
        assertEquals(details.getServiceDetails(),actual.getServiceDetails());
    }*/


}
