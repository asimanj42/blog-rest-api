package com.company.springbootblogrestapi.exception;

import org.springframework.http.HttpStatus;

public class NotBelongException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public NotBelongException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public NotBelongException(String message, HttpStatus httpStatus, String theMessage) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = theMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
