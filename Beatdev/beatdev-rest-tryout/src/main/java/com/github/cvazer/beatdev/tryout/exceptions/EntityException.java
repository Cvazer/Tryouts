package com.github.cvazer.beatdev.tryout.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityException extends RuntimeException{
    public EntityException(String message) {
        super(message);
    }
}
