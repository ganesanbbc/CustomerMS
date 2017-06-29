package com.cts.customer.dao;

import com.cts.customer.vo.Customer;


import com.cts.customer.vo.ServiceDetails;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.*;
import com.google.cloud.datastore.StructuredQuery.Filter;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

import org.springframework.stereotype.Repository;

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


            QueryResults<Entity> results = datastore.run(entityQuery);
            while(results.hasNext()){
                  Entity result = results.next();
            }


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
    
    public List<Customer> getCustomersById(String  customerName){

        try {
            log.warning("Calling getAllCustomers");
            ArrayList<Customer> customerList = new ArrayList();
//            Filter propertyFilter =
//            	    new Filter("height", FilterOperator.GREATER_THAN_OR_EQUAL, minHeight);
            Query<Entity> entityQuery = Query.newEntityQueryBuilder().setKind(TABLE_NAME).
            		setFilter(PropertyFilter. eq("Customer_Name",""+customerName)).build();

            Iterator<Entity> entityIterator = datastore.run(entityQuery);
            while (entityIterator.hasNext()) {
                Entity customer = entityIterator.next();
                if(customer.getKey().getId() != null && customer.getString("Customer_Name").startsWith(customerName)) {
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

    public void createServiceDetails(ServiceDetails service){

    }

}
