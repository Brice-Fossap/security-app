package com.brice.securityapp.exception;

import lombok.Getter;

@Getter
public class SecurityAppException extends RuntimeException {

    public SecurityAppException(String message) {
        super(message);
    }
}
