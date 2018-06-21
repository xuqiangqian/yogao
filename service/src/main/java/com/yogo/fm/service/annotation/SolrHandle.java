package com.yogo.fm.service.annotation;

import com.yogo.fm.queue.ExchangeEnum;
import com.yogo.fm.queue.QueueEnum;
import com.yogo.fm.solr.SolrHandleType;

import java.lang.annotation.*;

/**
 * 用于Solr相关操作，用在service层上
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SolrHandle {
    String value() default "";
    SolrHandleType type() default SolrHandleType.NULL;
    ExchangeEnum exchange() default ExchangeEnum.NULL;
    QueueEnum queue() default QueueEnum.NULL;
}
