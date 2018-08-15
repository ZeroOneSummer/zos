package com.dm.zos.common.exception;

import com.alibaba.fastjson.JSONObject;
import com.dm.zos.common.r.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Project zos.
 * Package: com.dm.zos.common
 * Description: 描述
 * Author: ZeroOneSummer
 * Date: 2018年08月14日 23:01
 */
@RestControllerAdvice
public class ZeroExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest req) {
        logger.error("异常:", e);
        R result = null;
        //业务异常
        if (e instanceof ZeroException) {
            Integer code = ((ZeroException) e).getCode();
            String msg = ((ZeroException) e).getMsg();
            result = R.error(code, msg);
        } else {//系统异常
            result = R.error();
        }
        logger.info("异常返回结果:{}", JSONObject.toJSONString(result));
        return result;

    }
}
