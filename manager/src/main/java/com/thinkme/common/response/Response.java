package com.thinkme.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkme.common.constants.code.CodeInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private String code;

    private String msg;

    /**
     * 返回数据
     */
    private T data;
    /**
     * 额外数据,不向前端展示Response
     */
    @JsonIgnore
    private Object extData;

    public Response of(CodeInterface codeInterface){
        return Response.builder().code(codeInterface.getCode()).msg(codeInterface.getMsg()).build();
    }


}
