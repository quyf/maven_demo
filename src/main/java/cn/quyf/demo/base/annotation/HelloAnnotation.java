package cn.quyf.demo.base.annotation;

import java.lang.annotation.*;

/**
 * @author quyf
 * @date 2019/7/9 9:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HelloAnnotation {
    
    String sayHi() default "Hi";   
}

