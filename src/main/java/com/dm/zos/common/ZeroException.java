package com.dm.zos.common;

import java.io.Serializable;

/**
 * Project zos.
 * Package: com.dm.zos.common
 * Description: 描述
 * Author: ZeroOneSummer
 * Date: 2018年08月14日 22:54
 */
public class ZeroException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;

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

    public ZeroException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ZeroException(Throwable cause, Integer code, String msg) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }
}
