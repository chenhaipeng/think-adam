package com.thinkme.base.exception;

public class NotFoundException extends BusinessException {

    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public NotFoundException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
