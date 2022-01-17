package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 61635
 * @Classname Remote
 * @Description TODO
 * @Date 2022/1/16 14:07
 * @Created by 61635
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Remote {
    String value();
}
