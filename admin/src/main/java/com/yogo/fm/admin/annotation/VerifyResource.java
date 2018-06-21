package com.yogo.fm.admin.annotation;

import java.lang.annotation.*;

/**
 * 拦截注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VerifyResource {
    /**
     * 拦截的资源
     *
     * @return
     */
    String value() default "";
}
