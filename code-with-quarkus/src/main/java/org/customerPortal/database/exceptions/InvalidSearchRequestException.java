package org.customerPortal.database.exceptions;

public class InvalidSearchRequestException extends RuntimeException {

    public InvalidSearchRequestException(String message){
        super(message);
    }

}
