package com.sportscenter.adapter.validations;

public class DuplicateColorException extends RuntimeException {
    public DuplicateColorException(String message) {
        super(message);
    }
}