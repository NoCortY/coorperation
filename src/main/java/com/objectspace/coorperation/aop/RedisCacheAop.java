package com.objectspace.coorperation.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.objectspace.coorperation.annotation.EnCache;
import com.objectspace.coorperation.util.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RedisCacheAop {
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(RedisCacheAop.class);
    @Autowired
    private RedisUtil redisUtil;
    private ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 拦截所有EnCache注解的方法
     */
    @Pointcut("@annotation(com.objectspace.coorperation.annotation.EnCache)")
    public void redisServicePoint(){

    }

    @Around(value="redisServicePoint()")
    public Object around(ProceedingJoinPoint joinPoint) {
        //获取自定义注解
        EnCache enCache = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(EnCache.class);
        //获取注解中需要的四个参数
        EnCache.CACHE_TYPE cacheType = enCache.cacheType();
        String key = enCache.key();
        int index = enCache.index();
        Class<?> targetClass = enCache.targetClass();
        //前置：从Redis里获取缓存
        //获取目标方法参数
        String appId = null;
        Object[] args = joinPoint.getArgs();
        if(args!=null && args.length>0) {
            appId = String.valueOf(args[index]);
        }
        //拼接参数
        String redisKey = key+":"+appId;
        Object object = null;
        //传枚举
        switch(cacheType) {
            case FIND:{//表示查询缓存
                object = findObject(joinPoint,redisKey,targetClass);
                break;
            }
            case UPDATE://表示更新缓存
                object = updateObject(joinPoint,redisKey);
                break;
        }
        return object;
    }
    private Object updateObject(ProceedingJoinPoint joinPoint, String redisKey) {
        //更新缓存，删除即可
        Object object = null;
        try {
            redisUtil.del(redisKey);
            object = joinPoint.proceed();
            System.out.println("更新数据，删除Redis缓存");
        }catch(Throwable e) {
            System.out.println("删除缓存时出现异常");
            System.out.println(e.getMessage());
        }
        return object;

    }
    private Object findObject(ProceedingJoinPoint joinPoint, String redisKey, Class<?> targetClass) {
        //检查缓存中是否有数据
        String result = redisUtil.get(redisKey);
        Object object = null;
        try {
            if(result==null || "failure".equals(result)){
                //表示缓存中没有数据，则查询数据库
                //执行目标方法查询数据库
                object = joinPoint.proceed();
                //将查询出的数据序列化放入缓存
                String objectJsonData = objectMapper.writeValueAsString(object);
                redisUtil.set(redisKey, objectJsonData);
                System.out.println("该操作通过查询SQL数据库获取");
                System.out.println("以将结果存入缓存");
            }else {
                //表示缓存中有数据,查询缓存
                object = objectMapper.readValue(result, targetClass);
                System.out.println("该操作通过命中Redis直接获取");
            }
        }catch(Throwable e) {
            System.out.println("Redis查询操作出现错误");
            System.out.println(e.getMessage());
        }
        return object;
    }
}

