package com.cts.customer.controller;


import com.cts.customer.utils.CustomerEndPoints;
import com.cts.customer.vo.CustomerDetails;
import com.cts.customer.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

       @RequestMapping(CustomerEndPoints.INDEX_URL)
    public ModelAndView showAddCustomerPage() {
        return new ModelAndView("addCustomer");
    }

    @RequestMapping(value = CustomerEndPoints.CREATE_CUSTOMER_URL, method = RequestMethod.POST)
    public void addCustomer() {
         customerService.addCustomer();
    }

    @RequestMapping(CustomerEndPoints.GET_CUSTOMERS_URL)
    public List<CustomerDetails> getCusotmerList() {
        return customerService.getPersons();
    }

}
