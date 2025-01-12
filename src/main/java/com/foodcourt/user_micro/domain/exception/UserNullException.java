package com.foodcourt.user_micro.domain.exception;

public class UserNullException extends RuntimeException {

    public UserNullException(String message)
    {
        super(message);
    }
}
