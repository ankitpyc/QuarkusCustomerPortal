package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.database.Customer;
import org.acme.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
@Path("customers")
public class CustomerManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerManager.class);
    @Inject
    CustomerService customerService;

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        LOGGER.info("Received Customer Creation Request : {}", customer);
        return customerService.updateCustomerDetails(customer);
    }

}
