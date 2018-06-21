package com.yogo.fm.service.system.impl;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.redis.RedisKeys;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.mapper.system.IResourceMapper;
import com.yogo.fm.model.system.ResourceEntity;
import com.yogo.fm.model.system.RoleEntity;
import com.yogo.fm.service.system.IResourceService;
import com.yogo.fm.service.system.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-18
 */
@Service
public class ResourceServiceImpl implements IResourceService {
    private final IResourceMapper resourceMapper;
    private final IRoleService roleService;

    @Autowired
    public ResourceServiceImpl(IResourceMapper resourceMapper, IRoleService roleService) {
        this.resourceMapper = resourceMapper;
        this.roleService = roleService;
    }

    @Override
    public ResourceEntity save(ResourceEntity entity) throws FmException {
        if (entity == null) {
            throw FmException.error("参数错误");
        }
        if (StringUtils.isBlank(entity.getName())) {
            throw FmException.error("菜单名不能为空");
        }
        if (StringUtils.isBlank(entity.getUrl())) {
            throw FmException.error("url不能为空");
        }
        if (entity.getType() == null) {
            throw FmException.error("菜单类型不能为空");
        }
        if (entity.getType() != 0 && entity.getParentId() == null) {
            throw FmException.error("父类Id不能为空");
        }
        resourceMapper.save(entity);
        return entity;
    }

    @Override
    public ResourceEntity delete(Long id) {
        return null;
    }

    @Override
    public List<ResourceEntity> batchDelete(List<Long> id) throws FmException {
        if (CollectionUtils.isEmpty(id)) {
            throw FmException.error("参数错误");
        }
        List<Long> resources = resourceMapper.findSubmenu(id);
        Set<Long> set = new HashSet<>();
        set.addAll(id);
        set.addAll(resources);
        List<Long> list = new ArrayList<>(set);
        resourceMapper.batchDelete(list);
        return null;
    }


    @Override
    public ResourceEntity update(ResourceEntity entity) throws FmException {
        if (entity.getId() == null) {
            throw FmException.error("参数错误");
        }
        resourceMapper.update(entity);
        return entity;
    }

    @Override
    public ResourceEntity find(Long id) {
        return resourceMapper.find(id);
    }

    @Override
    public List<ResourceEntity> batchSave(List<ResourceEntity> list) {
        return null;
    }

    @Override
    public FmPage<ResourceEntity> findPage(ResourceEntity condition, FmPage<ResourceEntity> page) throws FmException {
        if (page == null) {
            throw FmException.error("参数错误");
        }
        return page;
    }


    @Override
    @Cacheable(value = RedisKeys.ROLE_RESOURCE_LIST, key = "'role_resource_list_'+#roleId")
    public List<ResourceEntity> findResourceListByRoleId(Long roleId) {
        return resourceList(roleId);
    }

    @Override
    @CachePut(value = RedisKeys.ROLE_RESOURCE_LIST, key = "'role_resource_list_'+#roleId")
    public List<ResourceEntity> reloadResourceListByRoleId(Long roleId) {
        return resourceList(roleId);
    }

    private List<ResourceEntity> resourceList(Long roleId) {
        //通过查询角色资源集合，将角色资源集合保存到redis中
        if (roleId == null) {
            return new ArrayList<>();
        }
        List<ResourceEntity> resources = resourceMapper.findResourceByRoleId(roleId);
        List<Long> buttons = new ArrayList<>();
        resources.stream().filter(item -> item.getType() == 2).forEach(item -> buttons.add(item.getId()));
        List<ResourceEntity> collect = resources.stream().filter(item -> item.getType() == 1).collect(Collectors.toList());
        collect.forEach(resourceEntity ->
                resourceEntity.setResources(
                        resourceMapper.findByParentId(resourceEntity.getId())
                                .stream().filter(item -> buttons.contains(item.getId())).collect(Collectors.toList())
                )
        );
        return collect;
    }

    /**
     * 角色权限树
     *
     * @param roleId
     * @return
     */
    @Override
    @Cacheable(value = RedisKeys.ROLE_RESOURCE_TREE, key = "'role_resource_tree_'+#roleId")
    public List<ResourceEntity> findResourceTreeByRoleId(Long roleId) throws FmException {
        return resourceTree(roleId);
    }

    @Override
    @CachePut(value = RedisKeys.ROLE_RESOURCE_TREE, key = "'role_resource_tree_'+#roleId")
    public List<ResourceEntity> reloadResourceTreeByRoleId(Long roleId) throws FmException {
        return resourceTree(roleId);
    }

    private List<ResourceEntity> resourceTree(Long roleId) throws FmException {
        RoleEntity role = roleService.find(roleId);
        if (role == null) {
            return null;
        }
        List<ResourceEntity> resources = resourceMapper.findResourceByRoleId(role.getId());
        List<Long> pages = new ArrayList<>();
        resources.forEach(item -> pages.add(item.getId()));
        resources = resources.stream().filter(item -> item.getType() == 0).collect(Collectors.toList());
        resourceHandle(resources, pages);
        return resources;
    }

    private void resourceHandle(List<ResourceEntity> list, List<Long> pages) {
        list.forEach(resource -> {
            List<ResourceEntity> children = resourceMapper.findByParentId(resource.getId());
            children = children.stream().filter(item -> pages.contains(item.getId())).collect(Collectors.toList());
            resourceHandle(children, pages);
            resource.setResources(children);
        });
    }


    @Override
    public List<ResourceEntity> findAllPageResource() {
        return resourceMapper.findAllPageResource();
    }

    /**
     * 根据roleId查找权限树 新增时默认所有按钮check为false，修改时根据角色Id找到所属权限按钮，将check改为true
     *
     * @param roleId
     * @return
     */
    @Override
    public List<ResourceEntity> findResources(Long roleId) {
        List<ResourceEntity> list = resourceMapper.findByType(1);
        List<ResourceEntity> resources = findResourceListByRoleId(roleId);
        List<Long> buttons = new ArrayList<>();
        resources.forEach(item -> item.getResources().forEach(re -> buttons.add(re.getId())));
        list.forEach(item -> {
            List<ResourceEntity> children = resourceMapper.findByParentId(item.getId());
            children.forEach(child -> {
                if (buttons.contains(child.getId())) {
                    child.setCheck(true);
                }
            });
            item.setResources(children);
        });
        return list;
    }


    /**
     * 查找全部资源树
     *
     * @return
     */
    @Override
    public List<ResourceEntity> findAllResourceTree() {
        List<ResourceEntity> all = resourceMapper.findAll();
        List<ResourceEntity> resources = all.stream().filter(item -> item.getType() == 0).collect(Collectors.toList());
        resourcesErgodic(resources, all);
        return resources;
    }

    @Override
    public void reloadRoleResourceTreeAndList() {
        List<RoleEntity> roles = roleService.findAll();
        roles.forEach(role -> {
            try {
                reloadResourceTreeByRoleId(role.getId());
                reloadResourceListByRoleId(role.getId());
            } catch (FmException e) {
                e.printStackTrace();
            }
        });
    }

    private void resourcesErgodic(List<ResourceEntity> resources, List<ResourceEntity> all) {
        resources.forEach(item -> {
            List<ResourceEntity> list = all.stream().filter(item2 -> item.getId().equals(item2.getParentId())).collect(Collectors.toList());
            resourcesErgodic(list, all);
            item.setResources(list);
        });
    }
}
