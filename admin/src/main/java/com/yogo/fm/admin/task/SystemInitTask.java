package com.yogo.fm.admin.task;

import com.yogo.fm.service.system.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author qiqiang
 * @Description 系统启动之后将所有role的资源树和资源集合保存到redis中
 * @date 2018-05-25
 */
@Component
public class SystemInitTask implements ApplicationRunner {
    private final IResourceService resourceService;

    @Autowired
    public SystemInitTask(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public void run(ApplicationArguments applicationArguments){
        resourceService.reloadRoleResourceTreeAndList();
    }
}
