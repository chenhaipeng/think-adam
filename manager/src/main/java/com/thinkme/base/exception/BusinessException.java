package com.thinkme.base.exception;


import com.thinkme.common.response.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义业务逻辑异常类
 * <p>
 * <p>如果有更具体业务的自定义异常，请明确的定义，并继承此类</p>
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private Response response;

    public BusinessException(String errorMessage) {
        super(errorMessage);
    }

    public BusinessException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

}