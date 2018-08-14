package com.dm.zos.common;

import java.lang.annotation.*;

/**
 * Project zos.
 * Package: com.dm.zos.common
 * Description: 描述
 * Author: ZeroOneSummer
 * Date: 2018年08月14日 22:06
 */
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@SuppressWarnings("unchecked")
@Documented
public @interface Dev {
    String value() default "";
}
