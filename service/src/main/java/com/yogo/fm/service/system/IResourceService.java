package com.yogo.fm.service.system;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.model.system.ResourceEntity;
import com.yogo.fm.service.IBaseService;

import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-18
 */
public interface IResourceService extends IBaseService<ResourceEntity> {
    /**
     * 通过key从redis中获取角色资源集合
     *
     * @param roleId
     * @return
     */
    List<ResourceEntity> findResourceListByRoleId(Long roleId);

    /**
     * 重新加载缓存角色资源集合
     *
     * @param roleId
     * @return
     */
    List<ResourceEntity> reloadResourceListByRoleId(Long roleId) throws FmException;

    /**
     * 通过key删除缓存角色资源树
     *
     * @param roleId
     * @return
     */
    List<ResourceEntity> findResourceTreeByRoleId(Long roleId) throws FmException;

    /**
     * 重新加载缓存角色资源树
     *
     * @param roleId
     * @return
     */
    List<ResourceEntity> reloadResourceTreeByRoleId(Long roleId) throws FmException;

    /**
     * 查找所有有button的页面
     *
     * @return
     */
    List<ResourceEntity> findAllPageResource();

    /**
     * 查找页面及按钮树
     *
     * @return
     */
    List<ResourceEntity> findResources(Long roleId);

    /**
     * 查询所有资源树
     *
     * @return
     */
    List<ResourceEntity> findAllResourceTree();

    /**
     * 重新加载redis中角色资源树和集合
     */
    void reloadRoleResourceTreeAndList();

}
