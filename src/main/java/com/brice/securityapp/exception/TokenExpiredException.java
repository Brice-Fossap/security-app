package com.brice.securityapp.exception;

public class TokenExpiredException extends SecurityAppException  {
    public TokenExpiredException(String message) {
        super(message);
    }
}
