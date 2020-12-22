package com.jx.tdmp.common.lang;

import com.jx.tdmp.constant.ResultCode;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {
    private boolean success;//是否操作成功
    private HttpStatus code;//操作状态码，rest风格
    private String message;//操作结果详细信息
    private T data;//实体类数据

    public ResponseResult(ResultCode rc) {
        this.success = rc.isSuccess();
        this.code = rc.getCode();
        this.message = rc.getMessage();
    }

    public ResponseResult(ResultCode rc, T data) {
        this.success = rc.isSuccess();
        this.code = rc.getCode();
        this.message = rc.getMessage();
        this.data = data;
    }
//    public ResponseResult fail(ResultCode rc){
//        return new ResponseResult(rc);
//
//    }

}
