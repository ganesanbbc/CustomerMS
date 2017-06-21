package com.cts.customer.service;

import com.cts.customer.vo.Customer;

import java.util.List;

/**
 * Created by priyadarshini on 6/21/17.
 */
public interface ICustomerService {

    Customer createCustomer(Customer customer);

    List<Customer> getAllCustomers();
}
