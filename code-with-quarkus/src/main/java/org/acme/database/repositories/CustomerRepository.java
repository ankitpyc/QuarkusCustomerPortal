package org.acme.database.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.database.Customer;
import org.bson.Document;

import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheMongoRepository<Customer> {

    public List<Customer> getByNameAndCity(String name, int age) {
        return list("firstName=?1 and age=?2",name,age);
    }
}
