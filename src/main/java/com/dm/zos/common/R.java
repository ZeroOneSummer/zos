package com.dm.zos.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Administrator
 * @Description: TODO
 * @date 2018/8/14 18:42
 */
@ApiModel("响应实体")
public class R<T> {

    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "响应消息")
    private String msg;
    @ApiModelProperty(value = "响应数据")
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static R success() {
        return new R(RCode.SUCCESS_CODE, RCode.SUCCESS_MESSAGE);
    }

    public static <T> R success(T data) {
        return new R(RCode.SUCCESS_CODE, RCode.SUCCESS_MESSAGE, data);
    }

    public static R success(Integer code, String msg) {
        return new R(code, msg, null);
    }

    public static R error() {
        return new R(RCode.ERROR_CODE, RCode.ERROR_MESSAGE);
    }

    public static R error(Integer code, String msg) {
        return new R(code, msg);
    }
}
