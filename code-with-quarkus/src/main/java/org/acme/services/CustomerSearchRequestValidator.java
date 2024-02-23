package org.acme.services;

import io.netty.util.internal.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.database.Customer;
import org.acme.database.exceptions.InvalidCustomerDetailsException;
import org.acme.database.exceptions.InvalidSearchRequestException;
import org.acme.dto.CustomerSearchRequest;

@ApplicationScoped
public class CustomerSearchRequestValidator {
    public void validateSearchRequest(CustomerSearchRequest customerSearchRequest) {

        if (StringUtil.isNullOrEmpty(customerSearchRequest.getFirstName()) ||
                StringUtil.isNullOrEmpty(customerSearchRequest.getCity()) ||
                StringUtil.isNullOrEmpty(customerSearchRequest.getState()))
                {
            throw new InvalidSearchRequestException("Invalid SearchDetails  : Empty Data");
        }
    }
}
