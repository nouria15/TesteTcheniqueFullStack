package com.taskList.task.exceptions;

public class InsertionFailedException extends Exception {
    public InsertionFailedException(String errorMessage) {
        super(errorMessage);
    }
}
