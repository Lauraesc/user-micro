package com.foodcourt.user_micro.configuration.exceptionhandler;


import com.foodcourt.user_micro.domain.exception.EmailAlreadyExistsException;
import com.foodcourt.user_micro.domain.exception.RoleNoDataFoundException;
import com.foodcourt.user_micro.domain.exception.UserNoDataFoundException;
import com.foodcourt.user_micro.domain.exception.UserNotAdultException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionCodeResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        //que traiga el error de la primera posicion de la lista
        FieldError fieldError = ex.getFieldErrors().get(0);
        return ResponseEntity.badRequest().body(
                new ExceptionCodeResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), fieldError.getDefaultMessage(),
                        HttpStatus.BAD_REQUEST.name(), LocalDateTime.now()));
    }

    @ExceptionHandler(UserNoDataFoundException.class)
    public ResponseEntity<ExceptionCodeResponse> handleUserNullException(UserNoDataFoundException ex) {
        return ResponseEntity.badRequest().body(
                new ExceptionCodeResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage(),
                        HttpStatus.BAD_REQUEST.name(), LocalDateTime.now()));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ExceptionCodeResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionCodeResponse(
                String.valueOf(HttpStatus.CONFLICT.value()), ex.getMessage(), HttpStatus.CONFLICT.name(), LocalDateTime.now()));

    }

    @ExceptionHandler(RoleNoDataFoundException.class)
    public ResponseEntity<ExceptionCodeResponse> handleRoleNoDataFoundException(RoleNoDataFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionCodeResponse(
                String.valueOf(HttpStatus.NOT_FOUND.value()), ex.getMessage(), HttpStatus.NOT_FOUND.name(), LocalDateTime.now()));

    }

    @ExceptionHandler(UserNotAdultException.class)
    public ResponseEntity<ExceptionCodeResponse> handleUserNotAdultException(UserNotAdultException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionCodeResponse(
                String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage(), HttpStatus.BAD_REQUEST.name(), LocalDateTime.now()));

    }

}
