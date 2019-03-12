package com.he.syslog.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ TYPE })
@Documented
public @interface Module {

    /**
     * 模塊名字
     * 
     * @return
     */
    public String name();

    /**
     * 是否不记录日志，默认false
     */
    //public boolean ignoreLog() default false;
}
