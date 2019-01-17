package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TrackAlreadyExistsException extends Exception {
    public TrackAlreadyExistsException(String message){
        super(message);
    }
}
