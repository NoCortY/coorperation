package com.objectspace.coorperation.aop;


import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
public class AutoLogAop {
    private Logger logger = LoggerFactory.getLogger(AutoLogAop.class);
    /**
     * 自动打印日志
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.objectspace.coorperation.web.*.*.*(..))")
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
    public void afterThrowingLog(JoinPoint joinPoint,Exception e) {
        logger.error("===================================================================================");
        logger.error("异常所在类:"+joinPoint.getTarget().getClass().getName());
        logger.error("异常所在方法:"+joinPoint.getSignature().getName());
        logger.error("异常原因:"+e.getMessage());
        logger.error("===================================================================================");
    }
}
