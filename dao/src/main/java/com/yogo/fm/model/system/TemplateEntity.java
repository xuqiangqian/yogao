package com.yogo.fm.model.system;

import com.yogo.fm.model.BaseEntity;
import lombok.Data;

/**
 * @author qiqiang
 * @Description 模板
 * @date 2018-05-18
 */
@Data
public class TemplateEntity extends BaseEntity {
    /**
     * 模板名称
     */
    private String name;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 模板级别 0-系统集 不可删 1-普通级 可以删除
     */
    private Integer level;
}
