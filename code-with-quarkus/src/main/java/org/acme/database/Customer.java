package org.acme.database;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.acme.dto.Address;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSpendingLimit() {
        return spendingLimit;
    }

    public void setSpendingLimit(Double spendingLimit) {
        this.spendingLimit = spendingLimit;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}

