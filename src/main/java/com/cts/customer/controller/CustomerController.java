package com.cts.customer.controller;


import com.cts.customer.utils.CustomerEndPoints;
import com.cts.customer.vo.CustomerDetails;
import com.cts.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(CustomerEndPoints.INDEX_URL)
    public String getMessage() {
        return "success";
    }

    @RequestMapping(CustomerEndPoints.ROOT)
    public String getRootPath() {
        return "success";
    }

    @RequestMapping(CustomerEndPoints.GET_CUSTOMERS_URL)
    public List<CustomerDetails> getCusotmerList() {
        return customerService.getPersons();
    }


}
