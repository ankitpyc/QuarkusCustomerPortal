package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.database.Customer;
import org.acme.database.exceptions.InvalidCustomerDetailsException;
import org.acme.database.messaging.MessagePublisher;
import org.acme.database.repositories.CustomerRepository;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.slf4j.Logger;

@ApplicationScoped
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    @Inject
    CustomerRepository customerRepository;
    @Inject
    MessagePublisher messagePublisher;
    @Inject
    CustomerValidator customerValidator;

    public Response updateCustomerDetails(Customer customer) {

        try {
            customerValidator.validateCustomer(customer);
            customerRepository.persist(customer);
            messagePublisher.publishCustomer(customer);
        } catch (Exception exception) {
            if (exception instanceof InvalidCustomerDetailsException) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(exception.getMessage())
                        .build();
            }
        }
        String message = String.format("Customer Created Successfully with customer Id : %s", customer.getCustomerId());

        return Response.status(Response.Status.CREATED).entity(message).build();
    }

}
