package com.modsen.passengerservice.exceptions;

public class PhoneAlreadyExistsException extends RuntimeException{
    public PhoneAlreadyExistsException(String s) {
        super(s);
    }

}
