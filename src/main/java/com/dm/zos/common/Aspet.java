package com.dm.zos.common;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * Project zos.
 * Package: com.dm.zos.common
 * Description: 描述
 * Author: ZeroOneSummer
 * Date: 2018年08月14日 22:41
 */
@Aspect
@Component
public class Aspet {
    private static Logger logger = LoggerFactory.getLogger(Aspect.class);

    @Pointcut("execution(* com.dm.zos.controller.*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void beforeAdvice(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("请求地址:{},方法:{}" , request.getRequestURL().toString(),request.getMethod());
        logger.info("参数 :{} " ,JSONObject.toJSONString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret",pointcut = "pointCut()")
    public void afterRuturingAdivce(Object ret){
        logger.info("返回值 : " + JSONObject.toJSONString(ret));
    }

    @Around("pointCut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = joinPoint.proceed();
        logger.info("耗时 : {}ms" , (System.currentTimeMillis() - startTime));
        return ob;
    }
}
