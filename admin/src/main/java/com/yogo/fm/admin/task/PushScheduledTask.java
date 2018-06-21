package com.yogo.fm.admin.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-23
 */
@Component
public class PushScheduledTask {
    private static Logger logger = LoggerFactory.getLogger(PushScheduledTask.class);

    /**
     * 推送到App
     */
    @Scheduled(cron = "0/10 * * * * ? ")
    public void pushApp(){
    }
}
