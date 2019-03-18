package com.he.log;

import com.he.syslog.enums.LogMode;
import com.he.syslog.enums.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    LogMode mode() default LogMode.WEB;

    OperationType type();

    String operation();

    String desc() default "";

    boolean db() default false;
}
