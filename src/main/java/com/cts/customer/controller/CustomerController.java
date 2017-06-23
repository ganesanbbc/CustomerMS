package com.cts.customer.controller;


import com.cts.customer.utils.CustomerEndPoints;
import com.cts.customer.service.CustomerService;

import com.cts.customer.vo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = {CustomerEndPoints.ROOT, CustomerEndPoints.INDEX_URL})
    public String showCustomers(Model model) {
        List<Customer> customerList = customerService.getAllCustomers();
        model.addAttribute("customers",customerList);
        return "index";
    }

    @RequestMapping(CustomerEndPoints.CREATE_CUSTOMER_URL)
    public String showAddCustomerPage(Model model) {
        model.addAttribute("customer",new Customer());
        return "addCustomer";
    }

    @RequestMapping(value = CustomerEndPoints.CREATE_CUSTOMER_URL, method = RequestMethod.POST)
    public String createCustomer(@ModelAttribute("customer") Customer customer,Model model) {
        Customer returnCustomer = customerService.createCustomer(customer);
        model.addAttribute("customers",returnCustomer);
        return "addService";
    }



}
