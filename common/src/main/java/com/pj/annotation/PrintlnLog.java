package com.pj.annotation;

import java.lang.annotation.*;

/**
 * @version 1.0
 * @description 打印入参
 * @date 2024/3/8 15:20:23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface PrintlnLog {

    /**
     * 描述信息, 为空默认为 类名 + 方法名
     *
     * @return
     */
    String description() default "";

    /**
     * 是否打印返回内容
     *
     * @return
     */
    boolean returnValue() default false;

    /**
     * 方法执行耗时
     *
     * @return
     */
    boolean executionTime() default false;

}
