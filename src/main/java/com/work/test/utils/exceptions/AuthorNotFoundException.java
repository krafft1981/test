package com.work.test.utils.exceptions;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(Integer id) {

        super(String.format("Author with Id %d not found", id));
    }
}
