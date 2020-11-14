package com.system.virus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class VirusServiceException extends Exception {

    public VirusServiceException(Throwable e, String message) {
        super(message, e);
    }

}
