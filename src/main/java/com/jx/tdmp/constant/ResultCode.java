package com.jx.tdmp.constant;

import org.springframework.http.HttpStatus;

public enum ResultCode {
    ADD_ITEM_SUCCESS(true, HttpStatus.CREATED,"添加成功！"),
    ADD_ITEM_failure(false, HttpStatus.CREATED,"添加失败！"),

    NAME_CANNOT_BE_NULL(false, HttpStatus.BAD_REQUEST, "名称不能为空"),

    fail(false,HttpStatus.FORBIDDEN,""),

    succeed(true, HttpStatus.ACCEPTED,""),
    ;
    //操作是否成功
    boolean success;
    //操作代码
    HttpStatus code;
    //提示信息
    String message;
    private ResultCode(boolean success, HttpStatus code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    //getter&setter
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

