package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
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
    public Customer saveCustomer(Customer customer) {
        System.out.println("got request " + customer.firstName);
        customerService.updateCustomerDetails(customer);
        return customer;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomers(@PathParam("id") String id) {
        Customer customer = customerService.getCustomer(id);
        return customer;
    }

    @GET
    @Path("/nameAndCity/{name}/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomers(@PathParam("firstName") String name,@PathParam("age") int age) {
        List<Customer> customers = customerService.getByNameAndCity("Ankit",30);
        return customers;
    }
}
