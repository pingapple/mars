package com.franklin.mars.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);


    @Pointcut("execution(public * com.franklin.mars.controller.*.*(..))")
    private void log() {
    }

    @Before("log()")
    public void doBefore(org.aspectj.lang.JoinPoint joinpoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //        url
        logger.info("请求地址url={}", request.getRequestURL().toString());
//        method
        logger.info("HTTP method method={}", request.getMethod());
//        ip
        logger.info("ip={}", request.getRemoteAddr());
//        类方法
        logger.info("class_method={}", joinpoint.getSignature().getDeclaringTypeName()
                + "." + joinpoint.getSignature().getName());
        //参数
        logger.info("args={}", Arrays.toString(joinpoint.getArgs()));
    }

//    @After("log()")
//    public void doAfter() {
//        logger.info("doAfter");
//    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object);
    }


    @Around("log()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob 为方法的返回值
        logger.info("耗时 : " + (System.currentTimeMillis() - startTime) + " ms");
        return ob;
    }
}
