package com.example.imserver.annotation;

import java.lang.annotation.*;

/**
 * 不做登录校验的注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotRequireLogin {

}
