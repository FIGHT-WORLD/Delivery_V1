package com.fight_world.mono.global.exception;

import static com.fight_world.mono.global.response.ExceptionResponse.of;

import com.fight_world.mono.domain.user.exception.UserException;
import com.fight_world.mono.global.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionResponse> handleUserException(UserException e) {

        return ResponseEntity.status(e.getHttpStatus()).body(of(e.getMessage()));
    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(of(e.getLocalizedMessage()));
    }
}
