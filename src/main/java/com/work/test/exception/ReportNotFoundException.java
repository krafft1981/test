package com.work.test.exception;

public class ReportNotFoundException extends RuntimeException {

    public ReportNotFoundException(String type) {

        super(String.format("Report with type %s not found", type));
    }
}
