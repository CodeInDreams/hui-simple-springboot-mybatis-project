package com.example.demo.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessInfo {
    String name() default "";
    String description() default "";
}
