package com.yogo.fm.admin.aspect;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.queue.ExchangeEnum;
import com.yogo.fm.queue.QueueEnum;
import com.yogo.fm.queue.QueueProvider;
import com.yogo.fm.service.annotation.SolrHandle;
import com.yogo.fm.solr.SolrHandleType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author 作草分茶
 * @Description web请求日志切面
 * @date 2018-04-28
 */
@Aspect
@Component
@Order(2)
public class SolrAspect {
    private static Logger logger = LoggerFactory.getLogger(SolrAspect.class);
    private final QueueProvider queueProvider;

    @Autowired
    public SolrAspect(QueueProvider queueProvider) {
        this.queueProvider = queueProvider;
    }

    @Pointcut("@annotation(com.yogo.fm.service.annotation.SolrHandle)")
    public void solrHandler() {
    }

    /**
     * 处理请求前
     *
     * @param joinPoint
     */
    @Before("solrHandler()")
    public void doBefore(JoinPoint joinPoint) throws FmException {

    }

    /**
     * 处理请求结束
     *
     * @param object
     * @throws Throwable
     */
    @AfterReturning(returning = "object", pointcut = "solrHandler()")
    public void doAfterReturning(JoinPoint joinPoint, Object object) throws Throwable {
        //获取抽象方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取当前类的对象
        Class<?> clazz = joinPoint.getTarget().getClass();
        //获取当前类有 SolrHandle 注解的方法
        method = clazz.getMethod(method.getName(), method.getParameterTypes());
        SolrHandle solrHandle = method.getAnnotation(SolrHandle.class);
        //获取注解上的值
        SolrHandleType type = solrHandle.type();
        ExchangeEnum exchange = solrHandle.exchange();
        QueueEnum queue = solrHandle.queue();
        //发送消息
        switch (type) {
            case ADD:
            case UPDATE:
                queueProvider.send(object, exchange, queue);
                break;
            case DELETE:
                break;
            case FIND:
                break;
            default:
                break;
        }
    }
}
