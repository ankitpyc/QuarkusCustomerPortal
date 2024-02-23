package org.customerPortal;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.customerPortal.database.Customer;
import org.customerPortal.database.exceptions.InvalidSearchRequestException;
import org.customerPortal.dto.CustomerSearchRequest;
import org.customerPortal.dto.MatchCustomerRequests;
import org.customerPortal.dto.MatchResponse;
import org.customerPortal.services.CustomerSearchService;
import org.customerPortal.services.MatchCustomerService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("customers")
public class CustomerSearchManager {

    @Inject
    CustomerSearchService customerSearchService;

    @Inject
    MatchCustomerService matchCustomerService;

    @POST
    @Path("/search")
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

    @POST
    @Path("/matchCustomers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response matchCustomers(MatchCustomerRequests matchCustomerRequests) {

        MatchResponse matchResponse = null;
        try {
            matchResponse = matchCustomerService.getMatchingAndNonMatchingCustomer(matchCustomerRequests);
        } catch (Exception exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }

        return Response.status(Response.Status.OK).entity
                (matchResponse).build();
    }


}
