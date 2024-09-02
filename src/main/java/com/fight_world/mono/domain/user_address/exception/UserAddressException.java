package com.fight_world.mono.domain.user_address.exception;

import com.fight_world.mono.domain.user_address.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class UserAddressException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public UserAddressException(ExceptionMessage exceptionMessage) {
        super("[UserAddress Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {
        return exceptionMessage.getMessage();
    }
}
