package org.customerPortal.database.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;
import org.customerPortal.database.Customer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class CustomerRepository implements PanacheMongoRepository<Customer> {
    public List<Customer> findByNameAndCityAndStateOrFindAll(String name, String city, String state) {
        Document query = new Document();

        if (name != null) {
            query.append("firstName", name);
        }

        if (city != null) {
            query.append("addresses", new Document("$elemMatch", new Document("city", city)));
        }

        if (state != null) {
            query.append("addresses", new Document("$elemMatch", new Document("state", state)));
        }
        if (query.isEmpty())
            return listAll();

        return find(query).list();
    }
}
