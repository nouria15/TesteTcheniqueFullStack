package com.taskList.task.exceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public class ExceptionsHandler {
    public static ResponseEntity<String> handleException(Exception e, String className, String msg, HttpStatus status) {
        log.error(className, e);
        return new ResponseEntity<>(msg, status);
    }

    public static ResponseEntity<String> handleObjectNotExistException(String className, ObjectNotExistException e) {
        log.error(className, e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<String> handleInsertionFailedException(String className, InsertionFailedException e) {
        log.error(className, e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }





}
