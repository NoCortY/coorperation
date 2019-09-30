package com.objectspace.coorperation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * @author NoCortY
 *
 */
/*@Tartget定义：
 * public enum ElementType {
    // 类，接口（包括注解类型）或枚举的声明
    TYPE,

    // 属性的声明
    FIELD,

    // 方法的声明
    METHOD,

    // 方法形式参数声明
    PARAMETER，

    // 构造方法的声明
    CONSTRUCTOR,

    // 局部变量声明
    LOCAL_VARIABLE,

    //注解类型声明
    ANNOTATION_TYPE,

    //包的声明
    PACKAGE
}*/
@Target(ElementType.METHOD)//表示该注解只能用在方法上
/*
 * 如果一个注解被定义为RetentionPolicy.SOURCE，则它将被限定在Java源文件中，那么这个注解即不会参与编译也不会在运行期起任何作用，这个注解就和一个注释是一样的效果，只能被阅读Java文件的人看到；
 * 如果一个注解被定义为RetentionPolicy.CLASS，则它将被编译到Class文件中，那么编译器可以在编译时根据注解做一些处理动作，但是运行时JVM（Java虚拟机）会忽略它，我们在运行期也不能读取到；
 * 如果一个注解被定义为RetentionPolicy.RUNTIME，那么这个注解可以在运行期的加载阶段被加载到Class对象中。那么在程序运行阶段，我们可以通过反射得到这个注解，并通过判断是否有这个注解或这个注解中属性的值，从而执行不同的程序代码段。我们实际开发中的自定义注解几乎都是使用的RetentionPolicy.RUNTIME；
 * */
@Retention(RetentionPolicy.RUNTIME)
public @interface EnCache {

    String key();		//定义key值
    int index();		//定义参数位置  用户传多参时用
    Class<?> targetClass(); //定义目标类型
    CACHE_TYPE cacheType() default CACHE_TYPE.FIND;

    /**
     * 查找和更新缓存的处理方法不一样，更新逻辑为先删除再同步数据到缓存
     * @author Administrator
     *
     */
    enum CACHE_TYPE{
        FIND,		//定义查找
        UPDATE		//定义更新
    }
}
