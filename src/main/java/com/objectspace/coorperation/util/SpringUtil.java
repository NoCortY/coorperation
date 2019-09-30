package com.objectspace.coorperation.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 解决由于Bean加载先后顺序造成的MyBatis二级缓存无法注入的工具类
 * 出错包：com.asiainfo.sdzs.cache
 * 出错类：RedisCache
 * 出错原因：由于Cache在容器中是最先加载（作为二级缓存），所以在RedisUtil未被加载就被注入，导致注入为null
 * 解决方案：使用当前类，做一个懒加载，在使用到RedisUtil时再getBean，这样可以获取到已经被实例化的bean
 * @author NoCortY
 *
 */
@Component
@Lazy(false)
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {/*
    	System.out.println("================================================================");
    	System.out.println(clazz);
    	System.out.println(getApplicationContext());
    	System.out.println("================================================================");*/
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    private SpringUtil() {
    }
}
