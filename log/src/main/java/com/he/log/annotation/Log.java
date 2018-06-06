package com.he.log.annotation;
import com.he.model.enums.OperateModuleEnum;
import com.he.model.enums.OperateTypeEnum;

import java.lang.annotation.*;

/**
 * Target 用于设定注解使用范围
 * 取值	            注解使用范围
 * METHOD	        可用于方法上
 * TYPE	            可用于类或者接口上
 * ANNOTATION_TYPE	可用于注解类型上（被@interface修饰的类型）
 * CONSTRUCTOR	    可用于构造方法上
 * FIELD	        可用于域上
 * LOCAL_VARIABLE	可用于局部变量上
 * PACKAGE	        用于记录java文件的package信息
 * PARAMETER	    可用于参数上
 */
@Target(ElementType.METHOD)
/**
 * Retention 表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
 * SOURCE       在源文件中有效（即源文件保留）
 * CLASS        在class文件中有效（即class保留）
 * RUNTIME      在运行时有效（即运行时保留）
 */
@Retention(RetentionPolicy.RUNTIME)
/**
 * Documented 用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，
 * 因此可以被例如javadoc此类的工具文档化。Documented是一个标记注解，没有成员。
 */
@Documented
/**
 * @Inherited 元注解是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。
 * 如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
 */
@Inherited
public @interface Log {
    /**
     * 操作描述(业务名称)
     *
     * @return
     */
    String description() default "";

    /**
     * 操作模块
     *
     * @return
     */
    OperateModuleEnum module();

    /**
     * 操作类型
     *
     * @return
     */
    OperateTypeEnum opType();
}