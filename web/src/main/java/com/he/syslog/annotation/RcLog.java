package com.he.syslog.annotation;


import com.he.syslog.enums.LogMode;
import com.he.syslog.enums.OperationType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface RcLog {

    LogMode mode() default LogMode.WEB;// 日志模式：WEB(0, "WEB层日志"), FACE(1, "接口层日志"), SERVICE(2, "SERVICE层日志");

    OperationType type();// 操作类型：添加

    String operation();// 操作名称：添加订单

    String desc() default ""; // 操作内容：用户${user.name}添加了订单${order.name}

    boolean db() default false;// 是否要记录到数据库日志表

}
