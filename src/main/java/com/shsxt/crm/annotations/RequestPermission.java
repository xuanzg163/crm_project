package com.shsxt.crm.annotations;

import java.lang.annotation.*;

/**
 * 自定义注解，用来做权限
 * @author zhangxuan
 * @date 2018/10/20
 * @time 19:01
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestPermission {
    String aclValue() default "";
}
