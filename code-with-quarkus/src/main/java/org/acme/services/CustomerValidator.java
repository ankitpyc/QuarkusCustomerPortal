package org.acme.services;

import io.netty.util.internal.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.database.Customer;
import org.acme.database.exceptions.InvalidCustomerDetailsException;

@ApplicationScoped
public class CustomerValidator {
    public void validateCustomer(Customer customer) {

        System.out.println("customer.getFirstName : " + StringUtil.isNullOrEmpty(customer.getFirstName()));
        if (StringUtil.isNullOrEmpty(customer.getFirstName())) {
            System.out.println("customer.wdwdwd : " + customer.getFirstName());
            throw new InvalidCustomerDetailsException("Invalid Customer Details : Empty UserName");
        }

        if (customer.getAge() == null || customer.getAge() == 0) {
            throw new InvalidCustomerDetailsException("Invalid Customer Details : Empty or 0 age is not Allowed");
        }

    }
}
