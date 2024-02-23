package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.database.Customer;
import org.acme.database.exceptions.InvalidSearchRequestException;
import org.acme.dto.CustomerSearchRequest;
import org.acme.services.CustomerSearchRequestValidator;
import org.acme.services.CustomerSearchService;

import java.util.List;

@Path("customers/search")
public class CustomerSearchManager {

    @Inject
    CustomerSearchService customerSearchService;

    @Inject
    CustomerSearchRequestValidator customerSearchRequestValidator;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers(CustomerSearchRequest customerSearchRequest) {
        List<Customer> customers = null;
        try {
            customers = customerSearchService.search(customerSearchRequest);
        } catch (Exception exception) {
            if (exception instanceof InvalidSearchRequestException) {
                return Response.status(Response.Status.BAD_GATEWAY)
                        .entity(exception.getMessage())
                        .build();
            }
        }
        return Response.status(Response.Status.OK).entity(customers).build();
    }
}
