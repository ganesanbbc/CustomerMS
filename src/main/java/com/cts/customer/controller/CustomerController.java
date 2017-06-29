package com.cts.customer.controller;


import com.cts.customer.utils.CustomerEndPoints;
import com.cts.customer.service.CustomerService;

import com.cts.customer.vo.Customer;
import com.cts.customer.vo.ServiceDetails;
import com.googlecode.totallylazy.Strings;

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
    public ModelAndView createCustomer(@ModelAttribute("customer") Customer customer) {
        Customer returnCustomer = customerService.createCustomer(customer);
        ModelAndView view = new ModelAndView("addServiceDetails","service",new ServiceDetails(returnCustomer.getId()));
        return view;
    }
    
    @RequestMapping(value = CustomerEndPoints.SEARCH_CUSTOMER_URL, method = RequestMethod.GET)
    public String searchCustomer(String search,Model model) {
    	if(!Strings.isEmpty(search)){
    	 List<Customer> customerList = customerService.getCustomerById(search);
    	 model.addAttribute("customers",customerList);
    	}
    	 model.addAttribute("search", search);
        return CustomerEndPoints.SEARCH_CUSTOMER_URL;
    }



}
