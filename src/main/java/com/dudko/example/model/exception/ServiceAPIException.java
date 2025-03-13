package com.dudko.example.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ServiceAPIException extends RuntimeException {

    public ServiceAPIException(String message) {
        super(message);
    }

    public ServiceAPIException(String message, Throwable cause) {
        super(message, cause);
    }

}
