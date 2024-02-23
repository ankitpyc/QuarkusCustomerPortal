package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.database.Customer;
import org.acme.services.CustomerService;

import java.util.List;

@ApplicationScoped
@Path("customers")
public class CustomerManager {
    @Inject
    CustomerService customerService;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        System.out.println("got request " + customer.firstName);
        return customerService.updateCustomerDetails(customer);
    }

}
