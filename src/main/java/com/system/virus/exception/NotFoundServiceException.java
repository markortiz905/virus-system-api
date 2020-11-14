package com.system.virus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundServiceException extends Exception {

    public NotFoundServiceException(Throwable e, String message) {
        super(message, e);
    }

    public NotFoundServiceException(String message) {
        super(message);
    }

}
