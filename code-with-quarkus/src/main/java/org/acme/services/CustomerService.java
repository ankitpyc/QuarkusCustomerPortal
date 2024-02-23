package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.database.Customer;
import org.acme.database.exceptions.InvalidCustomerDetailsException;
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

    @Inject
    CustomerValidator customerValidator;

    public Customer getCustomer(String id) {
        Customer customer = customerRepository.findById(new ObjectId(id));
        return customer;
    }

    public Response updateCustomerDetails(Customer customer) {
        try {
            customerValidator.validateCustomer(customer);
            customerRepository.persist(customer);
            messagePublisher.publishCustomer(customer);
        } catch (Exception exception) {
            System.out.println("gpt eded");
            if (exception instanceof InvalidCustomerDetailsException) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(exception.getMessage())
                        .build();
            }
        }

        return Response.status(Response.Status.CREATED).entity("Customer Created Successfully").build();
    }

    public List<Customer> getByNameAndCity(String name, int age) {
        System.out.println("Getting name and CIryt" + name + " " + age);
        return customerRepository.getByNameAndCity(name, age);
    }

}
