package com.yogo.fm.model.system;

import com.yogo.fm.model.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @author qiqiang
 * @Description 模板明细表
 * @date 2018-05-18
 */
@Data
public class TemplateDetailEntity extends BaseEntity {
    /**
     * 模板名称
     */
    private String tName;
    /**
     * 模板Id
     */
    private Long tId;
    /**
     * 规则
     */
    private String rule;
    /**
     * 是否必填 0-否 1-是
     */
    private Boolean isWrite = false;
    /**
     * 启用禁用 0-禁用 1-启用
     */
    private Boolean isActive = true;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 数据集
     */
    private String dataList;

    /**
     * 字段名
     */
    private String field;

    private String fieldName;
    /**
     * 描述
     */
    private String description;
}
