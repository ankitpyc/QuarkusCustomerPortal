package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.database.Customer;
import org.acme.database.messaging.MessagePublisher;
import org.acme.database.repositories.CustomerRepository;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    MessagePublisher messagePublisher;


    public Customer getCustomer(String id){
        Customer customer = customerRepository.findById(new ObjectId(id));
        return customer;
    }

    public Customer updateCustomerDetails(Customer customer){
        customerRepository.persist(customer);
        messagePublisher.publishCustomer(customer);
        return customer;
    }

    public List<Customer> getByNameAndCity(String name,int age){
        System.out.println("Getting name and CIryt" + name +" " + age);
        return customerRepository.getByNameAndCity(name,age);
    }

}
