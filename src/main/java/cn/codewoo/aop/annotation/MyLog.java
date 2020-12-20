package cn.codewoo.aop.annotation;

import java.lang.annotation.*;

/**
 * @author kehong
 * 日记记录注解
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLog {
    /**
     * 模块名称
     * @return
     */
    String title() default "";

    /**
     * 功能
     * @return
     */
    String action() default "";
}
