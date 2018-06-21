package com.yogo.fm.queue;

import com.yogo.fm.model.system.AccountEntity;
import com.yogo.fm.solr.AccountSolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author qiqiang
 * @Description
 * @date 2018-06-07
 */
@Component
public class AccountConsumer {

    private static Logger logger = LoggerFactory.getLogger(AccountConsumer.class);
    private final AccountSolrClient accountSolrClient;

    @Autowired
    public AccountConsumer(AccountSolrClient accountSolrClient) {
        this.accountSolrClient = accountSolrClient;
    }

    /**
     * 新增账号
     * @param entity
     */
    @RabbitHandler
    @RabbitListener(queues = "account.add.queue")
    public void execute(AccountEntity entity) throws Exception {
        logger.info("接收到新增的消息，新增的账号id为"+entity.getId());
        accountSolrClient.update(entity.getId());
    }
}
