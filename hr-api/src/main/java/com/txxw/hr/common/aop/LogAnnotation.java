package com.txxw.hr.common.aop;
//自定义日志注解
import java.lang.annotation.*;
//.Type 代表可以放在类上面 .Method 代表可以放在方法上
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default "";

    String operator() default "";
}
