package com.foodcourt.user_micro.domain.exception;

public class UserNotAdultException extends RuntimeException {
    public UserNotAdultException(String message) {
        super(message);
    }
}
