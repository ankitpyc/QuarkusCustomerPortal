package org.acme.database.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.database.Customer;

import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheMongoRepository<Customer> {

    public List<Customer> getByNameAndCity(String name, int age) {
        return list("firstName=?1 and age=?2", name, age);
    }

    public List<Customer> findByNameAndCityAndStateOrFindAll(String name, String city, String state) {
        if (name != null && city != null && state != null) {
            List<Customer> matchingCustomers = list("firstName = ?1 and city = ?2 and state = ?3", name, city, state);
            if (matchingCustomers.isEmpty()) {
                return listAll(); // Return all customers if no match is found
            } else {
                return matchingCustomers;
            }
        } else {
            return listAll();
        }
    }
}
