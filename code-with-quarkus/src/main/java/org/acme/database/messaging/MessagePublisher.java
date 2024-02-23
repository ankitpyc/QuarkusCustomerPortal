package org.acme.database.messaging;

import jakarta.inject.Inject;
import io.smallrye.reactive.messaging.kafka.Record;
import org.acme.database.Customer;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

public class MessagePublisher {

    @Inject
    @Channel("newCustomer")
    Emitter<Record<String, Customer>> empEmitter;

    public void publishCustomer(Customer customer){
            empEmitter.send(Record.of(customer.firstName,customer));
            System.out.println("Recieveed messave");
    }

}
