package com.yogo.fm.admin.controller.system;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.response.FmResponse;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.common.utils.ListTypeUtils;
import com.yogo.fm.common.utils.ThreadLocals;
import com.yogo.fm.model.system.AccountEntity;
import com.yogo.fm.model.system.ResourceEntity;
import com.yogo.fm.service.system.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-18
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
    private final IResourceService resourceService;
    @Autowired
    public ResourceController(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * 登录到首页后获取资源
     *
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public FmResponse<List<ResourceEntity>> getAll() throws FmException {
        AccountEntity account = ThreadLocals.userLocal.get();
        //查找该用户的资源权限
        return FmResponse.ok("请求成功", resourceService.findResourceTreeByRoleId(account.getRoleId()));
    }

    /**
     * 获取该用户在某个页面的权限资源
     *
     * @param id
     * @return
     */
    @GetMapping("/button")
    public FmResponse<List<ResourceEntity>> getButton(@RequestParam("id") Long id) {
        AccountEntity accountEntity = ThreadLocals.userLocal.get();
        List<ResourceEntity> resources = resourceService.findResourceListByRoleId(accountEntity.getRoleId());
        for (ResourceEntity entity : resources) {
            if (id.equals(entity.getId())) {
                return FmResponse.ok("请求成功", entity.getResources());
            }
        }
        return FmResponse.ok("请求成功", null);
    }

    /**
     * 新增资源
     *
     * @param resourceEntity
     * @return
     * @throws FmException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public FmResponse<ResourceEntity> add(ResourceEntity resourceEntity) throws Exception {
        resourceService.save(resourceEntity);
        return FmResponse.ok("新增资源成功");
    }

    @DeleteMapping("/delete")
    public FmResponse<ResourceEntity> delete(@RequestParam("id") String id) throws FmException {
        List<String> ids = Arrays.asList(id.split(","));
        resourceService.batchDelete(ListTypeUtils.listStringToLong(ids));
        return FmResponse.ok("删除成功");
    }

    /**
     * 修改资源
     *
     * @param resourceEntity
     * @return
     * @throws FmException
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public FmResponse<ResourceEntity> update(ResourceEntity resourceEntity) throws FmException {
        resourceService.update(resourceEntity);
        return FmResponse.ok("修改资源成功");
    }

    /**
     * 通过查询条件和分页信息查询
     *
     * @param resourceEntity
     * @param fmPage
     * @return
     * @throws FmException
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public FmResponse<FmPage<ResourceEntity>> findPage(ResourceEntity resourceEntity, FmPage<ResourceEntity> fmPage) throws FmException {
        return FmResponse.ok("查扎成功", resourceService.findPage(resourceEntity, fmPage));
    }

    /**
     * 查找所有有按钮的页面
     * @return
     */
    @GetMapping(value = "/resourceWithButton")
    public FmResponse<List<ResourceEntity>> findPageResource(Long roleId) throws  FmException{
        return FmResponse.ok("查找成功",resourceService.findResources(roleId));
    }

}
