package com.thinkme.base.advice;

import com.thinkme.base.exception.BusinessException;
import com.thinkme.base.exception.InvalidRequestException;
import com.thinkme.base.exception.NotFoundException;
import com.thinkme.common.constants.code.RespCode;
import com.thinkme.common.response.Response;
import com.thinkme.utils.base.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class RestExceptionAdvice {

    /**
     * 请求参数校验失败
     *
     * @param ex 异常对象
     * @return
     */
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<Void> handleValidationException(InvalidRequestException ex, HttpServletRequest request) {
        log.info("invoking {} occurs  error : [{}]", request.getRequestURL().toString(), ex.getMessage());
        return new Response<Void>(RespCode.BAD_REQUEST.code, ex.getMessage(), null, null);
    }


    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Response<Void> handleValidationException(BusinessException ex) {
        if (BeanUtils.isNotEmpty(ex.getResponse())) {
            log.info("invoking occurs error : ", ex);
            return ex.getResponse();
        } else {
            log.info("invoking occurs error : ", ex);
            return new Response<Void>(RespCode.INTERNAL_SERVER_ERROR.code, ex.getMessage(), null, null);
        }

    }

    /**
     * 操作对象不存在,适用于数据查询出来的bean
     *
     * @param ex 异常对象
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response<Void> handleValidationException(NotFoundException ex) {
        log.info("invoking occurs error : ", ex);
        return new Response<Void>(RespCode.NOT_FOUND.code, ex.getMessage(), null, null);
    }

    /**
     * 服务器内部异常
     *
     * @param ex 异常对象
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Void> handleValidationException(Exception ex) {
        log.info("invoking occurs error : ", ex);
        return new Response<Void>(RespCode.INTERNAL_SERVER_ERROR.code, ex.getMessage(), null, null);
    }

    /**
     * 拦截空指针
     *
     * @param ex 异常对象
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleValidationException(NullPointerException ex) {
        log.info("invoking occurs error : ", ex);
        return Response.builder().code(RespCode.INTERNAL_SERVER_ERROR.code).build();
    }

}
