package com.yogo.fm.model.system;

import com.yogo.fm.model.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @author qiqiang
 * @Description 资源表
 * @date 2018-05-18
 */
@Data
public class ResourceEntity extends BaseEntity implements Comparable<ResourceEntity>{
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 父菜单id
     */
    private Long parentId;
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 包含的子资源
     */
    private List<ResourceEntity> resources;

    /**
     * 类型 0：目录，1：页面 2
     */
    private Integer type;
    /**
     * 图标
     */
    private String icon;

    private Integer sort;

    private Boolean check = false;


    @Override
    public int compareTo(ResourceEntity r) {
        return this.getSort().compareTo(r.getSort());

    }
}
