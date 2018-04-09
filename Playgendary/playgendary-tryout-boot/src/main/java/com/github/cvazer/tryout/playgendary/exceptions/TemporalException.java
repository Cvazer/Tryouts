package com.github.cvazer.tryout.playgendary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TemporalException extends RuntimeException{
    public TemporalException(String message) {
        super(message);
    }
}
