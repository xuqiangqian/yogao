package com.yogo.fm.admin.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author 作草分茶
 * @Description web请求日志切面
 * @date 2018-04-28
 */
@Aspect
@Component
@Order(1)
public class LogAspect {
    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);
    /**
     * 记录开始请求的时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.yogo.fm.admin.controller..*Controller..*(..))")
    public void webLog() {
    }

    /**
     * 在处理请求前记录
     *
     * @param joinPoint
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("IP :" + request.getRemoteAddr());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 处理请求结束日志记录
     *
     * @param object
     * @throws Throwable
     */
    @AfterReturning(returning = "object", pointcut = "webLog()")
    public void doAfterReturning(Object object) throws Throwable {
        logger.info("RESPONSE : " + JSONObject.toJSONString(object));
        logger.info("HANDLE_TIME : " + (System.currentTimeMillis() - startTime.get()));
        startTime.remove();
    }
}
