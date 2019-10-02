package com.objectspace.coorperation.aop;


import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

/** 
* @Description: 这是一个自动打印日志的类，基于Spring AOP技术，对Controller层的所有控制器进行日志打印
* @Author: Object
* @Date: 2019/9/30 
*/
@Component
@Aspect
public class AutoLogAop {
    //日志打印
    private Logger logger = LoggerFactory.getLogger(AutoLogAop.class);
    /**
     * @Description:  该方法使用环绕通知，对web层方法进行AOP切入，打印基础日志
     * @Param: [proceedingJoinPoint] 切入的方法
     * @return: java.lang.Object 方法的返回值
     * @Author: Object
     * @Date: 2019/10/1
     */
    @Around("execution(String com.objectspace.coorperation.web.*.*.*(..))")
    public Object aroundLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println();
        logger.info("===================================================================================");
        String userName = null;
        logger.debug(proceedingJoinPoint.getTarget().getClass().getName()+"."+proceedingJoinPoint.getSignature().getName()+"()"+" is starting.");
        //执行切入点
        Long startTimeMills = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed();
        Long endTimeMills = System.currentTimeMillis();
        /* 1.获取request信息 ======== 2.获取session信息 ======== 3.从session中获取登录用户信息 */
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();
        String loginInfo = (String) request.getSession().getAttribute("username");
        if(loginInfo == null || "".equals(loginInfo)) {
            userName = "游客";
        }else {
            userName = loginInfo;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String outputInfo = objectMapper.writeValueAsString(object);
        //获取请求地址
        String requestPath = request.getRequestURI();
        logger.info("请求地址:"+requestPath);
        logger.info("用户名:"+userName);
        logger.info("返回信息:"+outputInfo);
        logger.debug(proceedingJoinPoint.getTarget().getClass().getName()+"."+proceedingJoinPoint.getSignature().getName()+"()"+" running time is {} ms",endTimeMills-startTimeMills);
        logger.info("===================================================================================");
        System.out.println();
        return object;
    }
    /**
     * 抛出异常自动打印日志
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value="execution(* com.objectspace.coorperation.web.*.*.*(..))",throwing="e")
    /**
     * @Description:  该方法为异常后通知，抛出异常自动打印日志
     * @Param: [joinPoint, e] 切入点，异常对象
     * @return: void
     * @Author: Object
     * @Date: 2019/10/1
     */
    public void afterThrowingLog(JoinPoint joinPoint,Exception e) {
        logger.error("===================================================================================");
        logger.error("异常所在类:"+joinPoint.getTarget().getClass().getName());
        logger.error("异常所在方法:"+joinPoint.getSignature().getName());
        logger.error("异常原因:"+e.getMessage());
        logger.error("===================================================================================");
    }
}
