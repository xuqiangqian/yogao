package com.yogo.fm.admin.controller.system;

import com.yogo.fm.common.response.FmResponse;
import com.yogo.fm.service.system.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-29
 */
@RequestMapping("/systemConfig")
@RestController
public class SystemConfigController {
    private final IResourceService resourceService;

    @Autowired
    public SystemConfigController(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * 重新加载redis
     * @return
     */
    @GetMapping("/reloadRedis")
    public FmResponse reloadRedis() {
        //重新加载redis角色权限树和列表
        resourceService.reloadRoleResourceTreeAndList();
        return FmResponse.ok("重新加载成功");
    }
}
