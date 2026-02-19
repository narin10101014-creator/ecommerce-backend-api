package com.narin.ecommerce.exception;

public class MethodArgumentNotValidException extends RuntimeException {
    public MethodArgumentNotValidException(String message)  {
        super(message);
    }
}
