package org.acme.database;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.List;

@MongoEntity(collection = "persons")
public class Customer extends PanacheMongoEntity {
    public String firstName;
    public String lastName;
    public String customerId;
    public Integer age;
    public Double spendingLimit;
    public String mobileNumber;
    public List<Address> addresses;
}

