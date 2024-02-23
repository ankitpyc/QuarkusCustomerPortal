package org.acme.services;

import io.netty.util.internal.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.CustomerManager;
import org.acme.database.Customer;
import org.acme.database.exceptions.InvalidCustomerDetailsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class CustomerValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerValidator.class);

    public void validateCustomer(Customer customer) {

        LOGGER.info("Validating customer Request : {}", customer);
        if (StringUtil.isNullOrEmpty(customer.getFirstName())) {
            System.out.println("customer.wdwdwd : " + customer.getFirstName());
            throw new InvalidCustomerDetailsException("Invalid Customer Details : Empty UserName");
        }

        if (customer.getAge() == null || customer.getAge() == 0) {
            throw new InvalidCustomerDetailsException("Invalid Customer Details : Empty or 0 age is not Allowed");
        }

    }
}
