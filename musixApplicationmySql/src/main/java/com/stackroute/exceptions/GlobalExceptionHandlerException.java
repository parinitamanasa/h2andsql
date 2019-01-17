package com.stackroute.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandlerException {
    @ExceptionHandler({TrackAlreadyExistsException.class})
    public ResponseEntity<String> handleTrackAlreadyExists(TrackAlreadyExistsException trackAlreadyExists){
        return error(CONFLICT, trackAlreadyExists);
    }
    @ExceptionHandler({TrackNotFoundException.class})
    public ResponseEntity<String> handleTrackNotFound(TrackNotFoundException trackNotFound){
        return error(NOT_FOUND, trackNotFound);
    }
    @ExceptionHandler({SameTrackCommentsException.class})
    public ResponseEntity<String> handleSameTrackComments(SameTrackCommentsException sameTrackComments){
        return error(CONFLICT, sameTrackComments);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handle(Exception ex){
        return error(INTERNAL_SERVER_ERROR, ex);
    }
    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        log.error("Exception", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
