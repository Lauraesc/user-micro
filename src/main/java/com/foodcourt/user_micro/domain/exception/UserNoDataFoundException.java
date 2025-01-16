package com.foodcourt.user_micro.domain.exception;

public class UserNoDataFoundException extends RuntimeException {

    public UserNoDataFoundException(String message)
    {
        super(message);
    }
}
