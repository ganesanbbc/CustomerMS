package com.cts.customer.service;

import com.cts.customer.vo.Customer;
import com.cts.customer.vo.ServiceDetails;

import java.util.List;


public interface ICustomerService {

    Customer createCustomer(Customer customer);

    List<Customer> getAllCustomers();
    
    Customer getCustomerById(long customerId);
    
    List<Customer> searchCustomersByName(String customerName);

    void createServiceDetails(ServiceDetails service);
}
