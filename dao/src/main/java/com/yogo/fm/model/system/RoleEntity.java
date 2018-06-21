package com.yogo.fm.model.system;

import com.yogo.fm.model.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @author qiqiang
 * @Description 角色表
 * @date 2018-05-18
 */
@Data
public class RoleEntity extends BaseEntity {
    /**
     * 角色名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;
    /**
     * 可访问的资源
     */
   private List<ResourceEntity> resources;

}
