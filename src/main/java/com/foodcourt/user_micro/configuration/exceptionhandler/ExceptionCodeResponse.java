package com.foodcourt.user_micro.configuration.exceptionhandler;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
public class ExceptionCodeResponse {

    //esto es como personalizar la salida de errores

    private final String code;
    private final String message;
    private final String status;
    private final LocalDateTime timestamp;

}
