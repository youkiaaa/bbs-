package com.atyume.core.annotation;

import java.lang.annotation.*;

/**
 * 日志注解 拦截controller请求
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    String value() default "";

    String desc()  default "";

}
