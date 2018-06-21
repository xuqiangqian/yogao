package com.yogo.fm.admin.task;

import com.yogo.fm.service.system.IResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author qiqiang
 * @Description
 * @date 2018-06-07
 */
@Component
public class SystemScheduledTask {
    private static Logger logger = LoggerFactory.getLogger(SystemScheduledTask.class);
    private final IResourceService resourceService;

    @Autowired
    public SystemScheduledTask(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * 重新加载角色资源权限关系
     */
    @Scheduled(cron = "0 0 0/2 * * ? ")
    public void pushApp() {
        logger.info("重新加载角色资源权限关系");
        resourceService.reloadRoleResourceTreeAndList();
    }
}
