package com.cts.customer.dao;

import com.cts.customer.vo.Customer;

import com.google.cloud.Date;
import com.google.cloud.Timestamp;
import com.google.cloud.datastore.*;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by priyadarshini on 6/21/17.
 */

@Repository
public class CustomerDao {

    public static final String TABLE_NAME ="Customer";

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind(TABLE_NAME);


    public Customer createCustomer(Customer customer){
        Key key = datastore.allocateId(keyFactory.newKey());
        Entity entity = Entity.newBuilder(key).set("Customer_Name",customer.getUserName())
                .set("Customer_Email",customer.getMailId()).set("Customer_Location",customer.getLocation())
                .set("Created_DateTime", Timestamp.now()).build();

        datastore.add(entity);

        return customer;
    }

    public List<Customer> getAllCustomers(){

        List<Customer> customerList = new ArrayList<>();

        Query<Entity> entityQuery = Query.newEntityQueryBuilder().setKind(TABLE_NAME).build();

        Iterator<Entity> entityIterator = datastore.run(entityQuery);


        while (entityIterator.hasNext()){
            Entity customer = entityIterator.next();
            customerList.add(new Customer(customer.getKey().getId(),customer.getString("Customer_Name"),
                    customer.getString("Customer_Email"),customer.getString("Customer_Location"),
                    customer.getString("Created_DateTime")));

        }
        return  customerList;
    }

}
