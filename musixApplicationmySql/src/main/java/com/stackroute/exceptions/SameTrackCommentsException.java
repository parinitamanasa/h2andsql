package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SameTrackCommentsException extends Exception {
    public SameTrackCommentsException(String message){
        super(message);
    }
}
