package com.yogo.fm.mapper.system;

import com.yogo.fm.mapper.IBaseMapper;
import com.yogo.fm.model.system.RoleEntity;
import org.apache.ibatis.annotations.Param;

import javax.management.relation.RoleInfo;
import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-23
 */
public interface IRoleMapper extends IBaseMapper<RoleEntity> {
    /**
     * 查找所有角色id
     *
     * @return
     */
    List<RoleEntity> findAllName();

    String findNameById(Long id);

    List<RoleEntity> findAllById(List<Long> id);

    /**
     * 查询所有角色
     * @return
     */
    List<RoleEntity> findAll();

    /**
     * 根据条件查询所有角色
     * @param roleEntity
     * @return
     */
    List<RoleEntity> findAllRole(RoleEntity roleEntity);

    /**
     * 保存中间表关系
     * @param list
     * @param roleId
     */
    void saveRelation(@Param("list") List<Long> list,@Param("roleId")Long roleId);

    /**
     * 更新中间表关系
     * @param list
     * @param roleId
     */
    void updateRelation(List<Long> list,Long roleId);

    /**
     * 删除中间表关系
     * @param roled
     */
    void deleteRelation(Long roled);

}

