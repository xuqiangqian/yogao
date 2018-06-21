package com.yogo.fm.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.utils.BeanUtils;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.common.utils.FmPageUtils;
import com.yogo.fm.common.utils.ListTypeUtils;
import com.yogo.fm.mapper.system.IResourceMapper;
import com.yogo.fm.mapper.system.IRoleMapper;
import com.yogo.fm.model.system.ResourceEntity;
import com.yogo.fm.model.system.RoleEntity;
import com.yogo.fm.service.system.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-22
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleMapper roleMapper;
    @Autowired
    private IResourceMapper resourceMapper;


    @Override
    public RoleEntity save(RoleEntity entity) throws FmException {
        if (entity == null) {
            throw FmException.error("参数错误");
        }
        BeanUtils.onInsert(entity);
        roleMapper.save(entity);
        return entity;
    }


    @Override
    public RoleEntity delete(Long id) {
        roleMapper.delete(id);
        return null;
    }


    @Override
    public List<RoleEntity> batchDelete(List<Long> id) throws FmException {
        if (CollectionUtils.isEmpty(id)) {
            throw FmException.error("参数错误");
        }
        roleMapper.batchDelete(id);
        return null;
    }

    @Override
    public RoleEntity update(RoleEntity entity) throws FmException {
        BeanUtils.onUpdate(entity);
        return null;
    }

    /**
     * 通过id找资源
     *
     * @param roleId
     * @return
     */
    @Override
    public RoleEntity find(Long roleId) {
        RoleEntity roleEntity = roleMapper.find(roleId);
        if (roleEntity == null) {
            return null;
        }
        return roleEntity;
    }

    @Override
    public List<RoleEntity> batchSave(List<RoleEntity> list) {
        return null;
    }

    /**
     * 整理出角色-资源树
     *
     * @param role
     */
    private void roleResourceTreeHandle(RoleEntity role) {
        if (role == null) {
            return;
        }
        List<ResourceEntity> resources = role.getResources();
        Set<Long> resourceIds = new HashSet<>();
        if (!CollectionUtils.isEmpty(resources)) {
            //去除所有该角色的所有菜单id
            resources.forEach(resourceEntity -> resourceIds.add(resourceEntity.getId()));
            //留下一级菜单
            resources = resources.stream()
                    .filter(resourceEntity -> resourceEntity.getType() == 0)
                    .collect(Collectors.toList());
        }
        resources = new ArrayList<>(new TreeSet<>(resources));
        //过滤掉不属于该角色的资源和权限
        filter(resources, resourceIds, role.getId());
        role.setResources(resources);
    }

    /**
     * 过滤掉不属于该角色的资源和权限
     *
     * @param resources
     * @param resourceIds
     * @param id
     */
    private void filter(List<ResourceEntity> resources, Set<Long> resourceIds, Long id) {
        if (CollectionUtils.isEmpty(resources)) {
            return;
        }
        resources.forEach(resource -> {
            List<ResourceEntity> collect = resource.getResources().stream()
                    .filter(child -> resourceIds.contains(child.getId()))
                    .collect(Collectors.toList());
            filter(collect, resourceIds, id);
            resource.setResources(collect);
        });
    }


    @Override
    public FmPage<RoleEntity> findPage(RoleEntity condition, FmPage<RoleEntity> page) throws FmException {
        PageHelper.startPage(page.getPage(), page.getPageSize(), "createTime " + "desc");
        List<RoleEntity> allRole = roleMapper.findAllRole(condition);
        FmPageUtils.fmPageHandler(page, new PageInfo<>(allRole));
        //根据角色id找到对应的资源
        page.getData().forEach(role -> role.setResources(resourceMapper.findResourceByRole(role.getId())));
        return page;
    }

    @Override
    public List<RoleEntity> findAll() {
        return roleMapper.findAll();
    }

    /**
     * 查询所有name
     *
     * @return
     */
    @Override
    public String findNameById(Long id) {
        String name = roleMapper.findNameById(id);
        return name;
    }

    /**
     * 增加角色及对应权限
     *
     * @param role
     * @param id
     * @throws FmException
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void saveRoleAndRelation(RoleEntity role, String id) throws FmException {
        roleMapper.save(role);
        // TODO 保存中间关系
        List<String> ids = new ArrayList<>(Arrays.asList(id.split(",")));
        List<Long> longs = ListTypeUtils.listStringToLong(ids);
        longs.addAll(resourceMapper.findParentId(longs));
        roleMapper.saveRelation(longs, role.getId());
    }

    /**
     * 修改角色信息及对应权限
     *
     * @param roleEntity
     * @param id
     * @throws FmException
     */

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateRoleAndRelation(RoleEntity roleEntity, String id) throws FmException {
        if (roleEntity == null) {
            throw FmException.error("参数错误");
        }
        if (StringUtils.isBlank(id)) {
            throw FmException.error("参数错误");
        }
        roleMapper.update(roleEntity);
        //先删除再增加
        roleMapper.deleteRelation(roleEntity.getId());
        List<String> ids = new ArrayList<>(Arrays.asList(id.split(",")));
        List<Long> longs = ListTypeUtils.listStringToLong(ids);
        longs.addAll(resourceMapper.findParentId(longs));
        roleMapper.saveRelation(longs, roleEntity.getId());
    }
}
