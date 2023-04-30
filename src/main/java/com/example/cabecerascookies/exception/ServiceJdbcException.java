package com.example.cabecerascookies.exception;

public class ServiceJdbcException extends Exception{
    public ServiceJdbcException(String message) {
        super(message);
    }
    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause);
    }

}
