package com.cts.customer.dao;

import com.cts.customer.vo.Customer;


import com.cts.customer.vo.ServiceDetails;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Timestamp;
import com.google.cloud.datastore.*;
import com.google.cloud.datastore.StructuredQuery.Filter;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class CustomerDao {

    public static final String TABLE_NAME ="Customer";

    Logger log = Logger.getLogger("CustomerDao");

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

        try {
            log.warning("Calling getAllCustomers");
            ArrayList<Customer> customerList = new ArrayList();
            Query<Entity> entityQuery = Query.newEntityQueryBuilder().setKind(TABLE_NAME).build();


//            QueryResults<Entity> results = datastore.run(entityQuery);
//            while(results.hasNext()){
//                  Entity result = results.next();
//            }


            Iterator<Entity> entityIterator = datastore.run(entityQuery);
            while (entityIterator.hasNext()) {
                Entity customer = entityIterator.next();
                if(customer.getKey().getId() != null) {
                    Customer tempCustomer = new Customer(customer.getKey().getId(), customer.getString("Customer_Name"),
                            customer.getString("Customer_Email"), customer.getString("Customer_Location"),
                            "");
                    customerList.add(tempCustomer);
                }else{
                    log.info("Customer Key Id is NULL ");
                }
            }
            return customerList;
        } catch(Exception ex){
            log.warning("Exception : "+ex.getMessage());
            return  null;
        }
    }
    
    public Customer getCustomersById(long  customerId){
        try {
            log.warning("Calling getAllCustomers");
            Customer customerNew = new Customer();
            Query<Entity> entityQuery = Query.newEntityQueryBuilder().setKind(TABLE_NAME).
            		setFilter(PropertyFilter. eq("Customer_Id",""+customerId)).build();

            Iterator<Entity> entityIterator = datastore.run(entityQuery);
            while (entityIterator.hasNext()) {
                Entity customer = entityIterator.next();
                if(customer.getKey().getId() != null	) {
                	customerNew = new Customer(customer.getKey().getId(), customer.getString("Customer_Name"),
                            customer.getString("Customer_Email"), customer.getString("Customer_Location"),
                            "");
                }else{
                    log.info("Customer Key Id is NULL ");
                }
            }
            return customerNew;
        } catch(Exception ex){
            log.warning("Exception : "+ex.getMessage()+ ex.getMessage());
            return  null;
        }
    }
    
    public List<Customer> searchCustomersByName(String  customerName){
        try {
            log.warning("Calling searchCustomersByName"+customerName);
            ArrayList<Customer> customerList = new ArrayList();
            Query<Entity> entityQuery = Query.newEntityQueryBuilder().setKind(TABLE_NAME).
            		setFilter(PropertyFilter.ge("Customer_Name",""+customerName)).build();

            Iterator<Entity> entityIterator = datastore.run(entityQuery);
            while (entityIterator.hasNext()) {
                Entity customer = entityIterator.next();
                if(customer.getKey().getId() != null) {
                    Customer tempCustomer = new Customer(customer.getKey().getId(), customer.getString("Customer_Name"),
                            customer.getString("Customer_Email"), customer.getString("Customer_Location"),
                            "");
                    customerList.add(tempCustomer);
                }else{
                    log.info("searchCustomersByName Key Id is NULL ");
                }
            }
            log.info("searchCustomersByName customerList.size() "+customerList.size());
            return customerList;
        } catch(Exception ex){
            log.warning("Exception : "+ex.getMessage());
            return  null;
        }
    }

    public void createServiceDetails(ServiceDetails service){

    }

}
