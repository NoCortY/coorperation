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

/**
* @Description: 该类为Redis缓存AOP，使用AOP技术在切入带EnCache注解的方法时，自动访问缓存。
* @Author: Object
* @Date: 2019/10/1
*/
@Component
@Aspect
public class RedisCacheAop {
    private Logger logger = LoggerFactory.getLogger(RedisCacheAop.class);
    @Autowired
    private RedisUtil redisUtil;
    private ObjectMapper objectMapper = new ObjectMapper();
    /**
     * @Description: 自动拦截带有EnCache注解的方法
     * @Param:
     * @return:
     * @Author: Object
     * @Date: 2019/10/1
     */
    @Pointcut("@annotation(com.objectspace.coorperation.annotation.EnCache)")
    public void redisServicePoint(){}

    /**
     * @Description: 该方法使用环绕通知，切入带EnCache注解的方法前，先访问一次缓存，如果缓存中有则直接返回，没有则执行方法，最后回写缓存。
     * @Param: [joinPoint] 切入点
     * @return: java.lang.Object 切入点返回值
     * @Author: Object
     * @Date: 2019/10/1
     */
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
    /**
     * @Description:若方法为更新操作，则直接删除缓存相应的数据
     * @Param: [joinPoint, redisKey]
     * @return: java.lang.Object
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    private Object updateObject(ProceedingJoinPoint joinPoint, String redisKey) {
        //更新缓存，删除即可
        Object object = null;
        try {
            redisUtil.del(redisKey);
            object = joinPoint.proceed();
            logger.info("更新数据，删除Redis缓存");
        } catch (Throwable e) {
            logger.error("删除缓存时出现异常");
            logger.error("异常信息:"+e.getMessage());
        }
        return object;

    }
    /**
     * @Description:  该方法用于在执行切入点之前检查Redis中是否有数据
     * @Param: [joinPoint, redisKey] 切入点，Redis中存的key
     * @return: java.lang.Object 切入点返回值
     * @Author: Object
     * @Date: 2019/10/1
     */
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
            }else {
                //表示缓存中有数据,查询缓存
                object = objectMapper.readValue(result, targetClass);
            }
        }catch(Throwable e) {
            logger.error("Redis查询操作出现异常");
            logger.error("异常信息:"+e.getMessage());
        }
        return object;
    }
}

