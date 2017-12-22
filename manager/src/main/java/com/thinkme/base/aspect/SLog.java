package com.thinkme.base.aspect;

import java.lang.annotation.*;

/**
 * 采用注解的方式记录系统日志
 *
 * @author chenhaipeng
 * @version 1.0
 * @date 2017/07/07 17:16
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SLog {

    /**
     * 日志描述
     */
    String description() default "";

    /**
     * 是否要登录，默认为true
     *
     * @return
     */
    boolean needLogin() default true;

    /**
     * 是否进行jsr303校验，默认为true
     *
     * @return
     */
    boolean validate() default true;

    boolean logger() default true;

}
