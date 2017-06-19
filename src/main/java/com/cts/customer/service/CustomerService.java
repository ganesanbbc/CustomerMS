package com.cts.customer.service;

import com.cts.customer.vo.CustomerDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 237620 on 6/16/17.
 */

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
}
