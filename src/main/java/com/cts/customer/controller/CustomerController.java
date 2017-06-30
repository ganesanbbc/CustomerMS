package com.cts.customer.controller;


import com.cts.customer.utils.CustomerEndPoints;
import com.cts.customer.service.CustomerService;

import com.cts.customer.vo.Customer;
import com.cts.customer.vo.ServiceDetails;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {

    public static final String CUSTOMERS_ATTR = "customers";
    public static final String ADD_CUSTOMER_PAGE = "addCustomer";
    public static final String CUSTOMER_ATTR = "customer";
    public static final String SEARCH_ATTR = "search";


    public static final String INDEX_PAGE = "index";

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = {CustomerEndPoints.ROOT, CustomerEndPoints.INDEX_URL})
    public String showCustomers(Model model) {
        List<Customer> customerList = customerService.getAllCustomers();
        model.addAttribute(CUSTOMERS_ATTR, customerList);
        return INDEX_PAGE;
    }

    @RequestMapping(CustomerEndPoints.CREATE_CUSTOMER_URL)
    public String showAddCustomerPage(Model model) {
        model.addAttribute(CUSTOMER_ATTR, new Customer());
        return ADD_CUSTOMER_PAGE;
    }

    @RequestMapping(value = CustomerEndPoints.CREATE_CUSTOMER_URL, method = RequestMethod.POST)
    public ModelAndView createCustomer(@ModelAttribute("customer") Customer customer) {
        Customer returnCustomer = customerService.createCustomer(customer);
        return new ModelAndView("addServiceDetails",
                "service", new ServiceDetails(returnCustomer.getId()));
    }

//    @RequestMapping(value = CustomerEndPoints.SEARCH_CUSTOMER_URL, method = RequestMethod.GET)
//    public String searchCustomerGet(Model model) {
//        List<Customer> customerList = customerService.getAllCustomers();
//        model.addAttribute(CUSTOMERS_ATTR, customerList);
//        model.addAttribute(SEARCH_ATTR, "");
//        return CustomerEndPoints.SEARCH_CUSTOMER_URL;
//    }


    @RequestMapping(value = CustomerEndPoints.SEARCH_CUSTOMER_URL, method = RequestMethod.POST)
    public String searchCustomer(@RequestParam String search, Model model) {
        List<Customer> customerList = null;
        if (search != null && !search.isEmpty()) {
            customerList = customerService.searchCustomersByName(search);

        }
        model.addAttribute(CUSTOMERS_ATTR, customerList);
        model.addAttribute(SEARCH_ATTR, search);
        return INDEX_PAGE;
    }

    @RequestMapping(value = CustomerEndPoints.VIEW_CUSTOMER_URL, method = RequestMethod.GET)
    public String viewCustomer(@PathVariable String customerId, Model model) {
        if (customerId != null && !customerId.isEmpty()) {
            Customer customerList = customerService.getCustomerById(Long.parseLong(customerId));
            model.addAttribute(CUSTOMER_ATTR, customerList);
        }
//    	 model.addAttribute("search", search);
        return CustomerEndPoints.VIEW_CUSTOMER_URL;
    }



}
