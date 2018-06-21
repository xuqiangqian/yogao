package com.yogo.fm.queue;

import com.yogo.fm.common.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author qiqiang
 * @Description
 * @date 2018-06-07
 */
@Component
public class QueueProvider implements RabbitTemplate.ConfirmCallback {
    private static Logger logger = LoggerFactory.getLogger(QueueProvider.class);
    /**
     * 消息队列模板
     */
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public QueueProvider(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void  send(Object message, ExchangeEnum exchangeEnum, QueueEnum queueEnum) throws Exception {
        //设置回调为当前类对象
        rabbitTemplate.setConfirmCallback(this);
        //构建回调id为uuid
        CorrelationData correlationId = new CorrelationData(MD5Utils.uuid());
        //发送消息到消息队列
        rabbitTemplate.convertAndSend(exchangeEnum.getValue(), queueEnum.getRoutingKey(), message, correlationId);
        logger.info("message:"+message+",exchange:"+exchangeEnum.getValue()+",queue routingKey:"+queueEnum.getRoutingKey());
    }

    /**
     * 消息回调确认方法
     *
     * @param correlationData 请求数据对象
     * @param ack             是否发送成功
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            logger.info("消息发送成功，回调id：" + correlationData.getId());
        } else {
            System.out.println("消息发送失败，原因：" + cause + ",回调id：" + correlationData.getId());
        }
    }
}
