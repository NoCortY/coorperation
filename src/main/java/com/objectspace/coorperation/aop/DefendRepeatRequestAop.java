package com.objectspace.coorperation.aop;

import com.objectspace.coorperation.config.ConstantValue;
import com.objectspace.coorperation.util.RedisUtil;
import org.apache.shiro.util.Assert;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
* @Description: 防止页面请求重复提交，在Web层控制器运行时加入切面，验证是否已经提交过表单。
* @Author: NoCortY
* @Date: 2019/10/10
*/
@Component
@Aspect
public class DefendRepeatRequestAop {
    @Autowired
    private RedisUtil redisUtil;
    private Logger logger = LoggerFactory.getLogger(DefendRepeatRequestAop.class);

    /** 
    * @Description: 自动拦截带有@DefendRepeatRequest注解的方法
    * @Param:  
    * @return:  
    * @Author: NoCortY
    * @Date: 2019/10/10 
    */ 
    @Pointcut("@annotation(com.objectspace.coorperation.annotation.DefendRepeatRequest)")
    public void defendRepeatRequestPointCut(){}

    @Around(value="defendRepeatRequestPointCut()")
    public Object around(ProceedingJoinPoint joinPoint){
        //获取请求信息
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        //判断request是否为空
        Assert.notNull(request,"request is not null");
        String token = request.getRequestedSessionId();
        String path = request.getRequestURI();
        Long lockResult = redisUtil.setNx(ConstantValue.REQUEST_LOCK_KEY+":"+token+":"+path,"lock");
        Object object = null;
        Map<String,Object> modelMap = new HashMap<String,Object>();
        if(lockResult==1){
            try {
                object = joinPoint.proceed();
            } catch (Throwable throwable) {
                logger.error("访问方法"+joinPoint.getSignature().getName()+"异常!");
                logger.error("异常信息:"+throwable.getMessage());
                return object;
            }finally{
                redisUtil.del(ConstantValue.REQUEST_LOCK_KEY+":"+token+":"+path);
            }
        }else{
            logger.error("当前方法正在被访问，请勿重复提交请求!");
            modelMap.put(ConstantValue.TO_FRONTEND_FLAG,false);
            modelMap.put(ConstantValue.TO_FRONTEND_MESSAGE,"请勿重复提交请求!");
            return modelMap;
        }
        return object;
    }
}
