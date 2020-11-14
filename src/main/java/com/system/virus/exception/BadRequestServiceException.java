package com.system.virus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestServiceException extends Exception {

    public BadRequestServiceException(Throwable e, String message) {
        super(message, e);
    }

}
