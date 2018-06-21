package com.yogo.fm.service.system;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.model.system.RoleEntity;
import com.yogo.fm.service.IBaseService;

import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-22
 */
public interface IRoleService extends IBaseService<RoleEntity> {
    /**
     * 查找所有角色树
     * @return
     */
    List<RoleEntity> findAll();

    /**
     * 根据Id找name
     * @return
     */
    String findNameById(Long id);

    void saveRoleAndRelation(RoleEntity role, String id) throws FmException;

    void updateRoleAndRelation(RoleEntity roleEntity,String id) throws FmException;


}