package com.work.test.utils.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Integer id) {

        super(String.format("Book with Id %d not found", id));
    }
}
