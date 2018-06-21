package com.yogo.fm.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author qiqiang
 * @Description
 * @date 2018-06-07
 */
@Configuration
public class AccountConfig {
    /**
     * 配置路由交换对象实例
     * @return
     */
    @Bean
    public DirectExchange accountDirectExchange()
    {
        return new DirectExchange(ExchangeEnum.ACCOUNT_EXCHANGE.getValue());
    }

    /**
     * 配置用户注册队列对象实例
     * 并设置持久化队列
     * @return
     */
    @Bean
    public Queue accountAddQueue()
    {
        return new Queue(QueueEnum.ACCOUNT_ADD.getName(),true);
    }

    /**
     * 将用户注册队列绑定到路由交换配置上并设置指定路由键进行转发
     * @return
     */
    @Bean
    public Binding accountAddBinding()
    {
        return BindingBuilder.bind(accountAddQueue()).to(accountDirectExchange()).with(QueueEnum.ACCOUNT_ADD.getRoutingKey());
    }
}
