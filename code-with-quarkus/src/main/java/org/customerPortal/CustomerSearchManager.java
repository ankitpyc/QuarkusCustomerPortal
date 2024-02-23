package org.customerPortal;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.customerPortal.database.Customer;
import org.customerPortal.database.exceptions.InvalidSearchRequestException;
import org.customerPortal.dto.CustomerSearchRequest;
import org.customerPortal.dto.MatchResponse;
import org.customerPortal.services.CustomerSearchService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("customers/search")
public class CustomerSearchManager {

    @Inject
    CustomerSearchService customerSearchService;

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

    @POST
    @Path("/matchCustomers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response matchCustomers(MatchCustomerRequests matchCustomerRequests) {

        List<Customer> customerListA = matchCustomerRequests.getCustomersListA();
        List<Customer> customerListB = matchCustomerRequests.getCustomersListB();

        // Optimize using Java Streams and HashSet
        Set<Customer> setA = customerListA.stream().collect(Collectors.toSet());
        Set<Customer> setB = customerListB.stream().collect(Collectors.toSet());

        // Common Customers
        Set<Customer> commonCustomers = setA.stream().filter(setB::contains).collect(Collectors.toSet());

        // Customers Only in A
        Set<Customer> customersOnlyInA = setA.stream().filter(c -> !setB.contains(c)).collect(Collectors.toSet());

        // Customers Only in B
        Set<Customer> customersOnlyInB = setB.stream().filter(c -> !setA.contains(c)).collect(Collectors.toSet());
        // All Customers
        Set<Customer> allCustomers = new HashSet<>(setA);
        allCustomers.addAll(setB);
        MatchResponse matchResponse = new MatchResponse();
        matchResponse.setInBothList(commonCustomers.stream().toList());
        matchResponse.setOnlyInListB(customersOnlyInB.stream().toList());
        matchResponse.setOnlyInListA(customersOnlyInA.stream().toList());

        return Response.status(Response.Status.OK).entity
                (matchResponse).build();
    }


}
