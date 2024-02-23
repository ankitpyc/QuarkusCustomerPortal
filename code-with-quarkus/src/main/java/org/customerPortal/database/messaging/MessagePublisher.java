package org.customerPortal.database.messaging;

import jakarta.inject.Inject;
import io.smallrye.reactive.messaging.kafka.Record;
import org.customerPortal.database.Customer;
import org.customerPortal.services.CustomerValidator;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessagePublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerValidator.class);
    @Inject
    @Channel("newCustomer")
    Emitter<Record<String, Customer>> empEmitter;

    public void publishCustomer(Customer customer) {
        LOGGER.info("Publishing Customer message to topic : {}", customer.getCustomerId());
        empEmitter.send(Record.of(String.valueOf(customer.getCustomerId()), customer));
        System.out.println("Recieveed messave");
    }

}
