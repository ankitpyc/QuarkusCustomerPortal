package org.customerPortal.services;

import io.netty.util.internal.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import org.customerPortal.database.exceptions.InvalidSearchRequestException;
import org.customerPortal.dto.CustomerSearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class CustomerSearchRequestValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerSearchRequestValidator.class);

    public void validateSearchRequest(CustomerSearchRequest customerSearchRequest) {

        if (StringUtil.isNullOrEmpty(customerSearchRequest.getFirstName()) ||
                StringUtil.isNullOrEmpty(customerSearchRequest.getCity()) ||
                StringUtil.isNullOrEmpty(customerSearchRequest.getState()))
                {
            throw new InvalidSearchRequestException("Invalid SearchDetails  : Empty Data");
        }
    }
}
