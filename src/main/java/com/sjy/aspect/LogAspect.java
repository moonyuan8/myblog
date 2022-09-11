package com.sjy.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 傻傻的远
 * @date 2021 11 18:06
 * @Description:
 */

//@Aspect       //拦截器
//@Component    //组件
//public class LogAspect {
//
//    private final Logger logger=LoggerFactory.getLogger(this.getClass());
//    @Pointcut("execution(* com.sjy.web.*.*(..))")   //设置切面
//    public void log(){};
//
//    @Before("log()")   //切面前
//    public void doBefore(JoinPoint joinPoint)
//    {
//        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        String url=request.getRequestURL().toString();
//        String ip=request.getRemoteAddr();
//        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
//        Object[]args=joinPoint.getArgs();
//        requestLog log=new requestLog(url,ip,classMethod,args);
//        logger.info("before**********************");
//        logger.info("request: {}",log);
//    }
//
//    @After("log()")   //切面前
//    public void doAfter()
//    {
//        logger.info("after**********************");
//    }
//
//    @AfterReturning(returning = "result",pointcut = "log()")
//    public void doAfterReturn(Object result)
//    {
//        logger.info("result: {}",result);
//    }
//
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    private   class requestLog
//    {
//        private String url;
//        private String ip;
//        private String classMethod;
//        private Object[]args;
//    }
//}
