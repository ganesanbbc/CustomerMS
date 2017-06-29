package com.cts.customer.service;

import com.cts.customer.dao.CustomerDao;
import com.cts.customer.vo.Customer;
import com.cts.customer.vo.ServiceDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {


    @Autowired
    CustomerDao customerDao;

    @Override
    public Customer createCustomer(Customer customer) {
            return customerDao.createCustomer(customer);
        }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public void createServiceDetails(ServiceDetails service) {
         customerDao.createServiceDetails(service);
    }

	@Override
	public List<Customer> getCustomerById(String customerName) {
		return customerDao.getCustomersById(customerName);
	}
}
