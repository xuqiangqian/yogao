package com.yogo.fm.admin.queue;

import com.yogo.fm.YogoAdminApplicationTest;
import com.yogo.fm.model.system.AccountEntity;
import com.yogo.fm.queue.QueueProvider;
import com.yogo.fm.service.system.IAccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author qiqiang
 * @Description
 * @date 2018-06-07
 */
public class QueueProviderTest extends YogoAdminApplicationTest {
    @Autowired
    private QueueProvider queueProvider;
    @Autowired
    private IAccountService accountService;
    @Test
    public void providerTest() throws Exception {
        AccountEntity accountEntity = accountService.find(10001L);
        accountEntity.setId(null);
        accountEntity.setUsername("solr200");
        accountEntity.setMobile("12368425622");
        accountService.save(accountEntity);
    }
}
