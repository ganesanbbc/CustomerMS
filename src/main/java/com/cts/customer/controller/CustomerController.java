package com.cts.customer.controller;


import com.cts.customer.utils.CustomerEndPoints;
import com.cts.customer.service.CustomerService;

import com.cts.customer.vo.Customer;
import com.cts.customer.vo.ServiceDetails;


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
    public String searchCustomerGet(Model model) {
    	 List<Customer> customerList = customerService.getAllCustomers();
    	  model.addAttribute("customers",customerList);
        return CustomerEndPoints.SEARCH_CUSTOMER_URL;
    }
    
    
    @RequestMapping(value = CustomerEndPoints.SEARCH_CUSTOMER_URL, method = RequestMethod.POST)
    public String searchCustomer(String search,Model model) {
    	List<Customer> customerList = null;
    	if(search != null && !search.isEmpty()){
    	customerList = customerService.searchCustomersByName(search);
    	 
    	}
    	model.addAttribute("customers",customerList);
    	 model.addAttribute("search", search);
        return CustomerEndPoints.SEARCH_CUSTOMER_URL;
    }
    
    @RequestMapping(value = CustomerEndPoints.VIEW_CUSTOMER_URL, method = RequestMethod.GET)
    public String viewCustomer(String customerId,Model model) {
    	if(customerId != null && !customerId.isEmpty()){
    	 Customer customerList = customerService.getCustomerById(Long.parseLong(customerId));
    	 model.addAttribute("customer",customerList);
    	}
//    	 model.addAttribute("search", search);
        return CustomerEndPoints.VIEW_CUSTOMER_URL;
    }



}
