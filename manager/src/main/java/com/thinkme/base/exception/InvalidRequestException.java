package com.thinkme.base.exception;

/**
 * 校验异常逻辑
 */
public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String errorMessage){
        super(errorMessage);
    }

    public InvalidRequestException(String errorMessage, Throwable cause){
        super(errorMessage, cause);
    }

}