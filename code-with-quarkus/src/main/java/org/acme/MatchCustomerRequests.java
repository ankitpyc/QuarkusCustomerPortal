package org.acme;

import org.acme.database.Customer;

import java.util.List;

public class MatchCustomerRequests {
    List<Customer> customersListA;
    List<Customer> customersListB;

    public List<Customer> getCustomersListA() {
        return customersListA;
    }

    public void setCustomersListA(List<Customer> customersListA) {
        this.customersListA = customersListA;
    }

    public List<Customer> getCustomersListB() {
        return customersListB;
    }

    public void setCustomersListB(List<Customer> customersListB) {
        this.customersListB = customersListB;
    }
}