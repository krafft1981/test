package com.work.test.utils.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Integer id) {

        super(String.format("Order with Id %d not found", id));
    }
}
