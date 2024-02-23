package org.customerPortal.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.customerPortal.database.Customer;
import org.customerPortal.dto.MatchCustomerRequests;
import org.customerPortal.dto.MatchResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class MatchCustomerService {

    public MatchResponse getMatchingAndNonMatchingCustomer(MatchCustomerRequests matchCustomerRequests) {
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

       return matchResponse;
    }
}
