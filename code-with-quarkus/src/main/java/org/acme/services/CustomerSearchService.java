package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.database.Customer;
import org.acme.database.repositories.CustomerRepository;
import org.acme.dto.CustomerSearchRequest;

import java.util.List;

@ApplicationScoped
public class CustomerSearchService {
    @Inject
    CustomerRepository customerRepository;


    public List<Customer> search(CustomerSearchRequest customerSearchRequest) {
        return customerRepository.findByNameAndCityAndStateOrFindAll(customerSearchRequest.getFirstName(),
                customerSearchRequest.getCity(),
                customerSearchRequest.getState());
    }
}
