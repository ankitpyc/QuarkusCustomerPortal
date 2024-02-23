package org.customerPortal.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.customerPortal.database.Customer;
import org.customerPortal.database.repositories.CustomerRepository;
import org.customerPortal.dto.CustomerSearchRequest;

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
