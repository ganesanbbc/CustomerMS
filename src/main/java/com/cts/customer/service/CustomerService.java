package com.cts.customer.service;

import com.cts.customer.vo.CustomerDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {



    public List<CustomerDetails> getPersons() {
        List<CustomerDetails> arrayList = new ArrayList() {
            {
                add(new CustomerDetails("firstname"));
            }
        };

        return arrayList;
    }

    public void addCustomer() {



    }
}
