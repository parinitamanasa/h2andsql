package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResponseStatusHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({TrackNotFoundException.class})
    public void handle(TrackNotFoundException e){ }

//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ExceptionHandler({TrackAlreadyExistsException.class})
//    public void handle(TrackAlreadyExistsException e){ }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({TrackAlreadyExistsException.class, NullPointerException.class, Exception.class})
    public void handle(){ }
}
