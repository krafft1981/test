package com.work.test.utils.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Integer id) {

        super(String.format("Customer with Id %d not found", id));
    }
}
