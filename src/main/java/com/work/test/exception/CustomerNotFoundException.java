package com.work.test.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Integer id) {

        super(String.format("Customer with Id %d not found", id));
    }
}
