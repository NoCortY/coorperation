package com.objectspace.coorperation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @Description: 自定义注解，用来配合AOP防止表单重复提交 
* @Author: NoCortY
* @Date: 2019/10/10 
*/
//表示该注解只能用在方法上
@Target(ElementType.METHOD)
//运行时读取
@Retention(RetentionPolicy.RUNTIME)
public @interface DefendRepeatRequest {
}
