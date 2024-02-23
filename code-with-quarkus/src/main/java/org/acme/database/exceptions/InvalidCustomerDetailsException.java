package org.acme.database.exceptions;

public class InvalidCustomerDetailsException extends RuntimeException {

    public InvalidCustomerDetailsException(String message){
        super(message);
    }

}
