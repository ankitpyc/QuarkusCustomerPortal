package org.customerPortal.messaging;

import jakarta.inject.Inject;
import io.smallrye.reactive.messaging.kafka.Record;
import org.customerPortal.database.Customer;
import org.customerPortal.services.CustomerValidator;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessagePublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerValidator.class);
    @Inject
    @Channel("newCustomer")
    Emitter<Customer> empEmitter;

    public void publishCustomer(Customer customer) {
        LOGGER.info("Publishing Customer message to topic : {}", customer.getCustomerId());
        empEmitter.send(customer)
                .exceptionally(e -> handleFailure(customer, e));
        System.out.println("Recieveed messave");
    }
    private Void handleFailure(Customer customer, Throwable throwable) {
        LOGGER.error("Failed to publish message to kafka for customer: {}", customer.getCustomerId());
        LOGGER.error("Error details:", throwable);
        return null; // or throw a specific exception if you want to propagate it further
    }

}
