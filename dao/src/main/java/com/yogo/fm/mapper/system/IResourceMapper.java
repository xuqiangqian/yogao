package com.yogo.fm.mapper.system;

import com.yogo.fm.mapper.IBaseMapper;
import com.yogo.fm.model.system.ResourceEntity;

import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-18
 */
public interface IResourceMapper extends IBaseMapper<ResourceEntity> {

    /**
     * 查询所有含有button的资源
     * @return
     */
    List<ResourceEntity> findAllPageResource();

//    List<ResourceEntity> findByParentIdIn(Collection<Long> parentId);

    List<ResourceEntity> findResourceByRoleId(Long id);
    /**
     * 根据parentId找资源
     * @param parentId
     * @return
     */
    List<ResourceEntity> findByParentId(Long parentId);
    /**
     * 根据类别查找菜单
     * @param type
     * @return
     */
    List<ResourceEntity> findByType(Integer type);

    /**
     * 根据parentId找上级
     * @param parentId
     * @return
     */
    List<Long> findParentId (List<Long> parentId);

    /**
     *根据角色查找对应权限（只查询type=1的资源）
     * @param roleId
     * @return
     */
    List<ResourceEntity> findResourceByRole(Long roleId);

    /**
     * 查询所有资源信息
     * @return
     */
    List<ResourceEntity> findAll();

    /**
     * 根据id查询其子集的id
     * @param resourceId
     * @return
     */
    List<Long> findSubmenu(List<Long> resourceId);
}
