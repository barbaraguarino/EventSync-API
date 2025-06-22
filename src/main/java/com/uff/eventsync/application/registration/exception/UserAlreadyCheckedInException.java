package com.uff.eventsync.application.registration.exception;

public class UserAlreadyCheckedInException extends RuntimeException {
    public UserAlreadyCheckedInException(String message) {
        super(message);
    }
}
