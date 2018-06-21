package com.yogo.fm.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qiqiang
 * @Description 基类
 * @date 2018-05-18
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String SYSTEM_ADMIN = "system_admin";

    private Long id = 10001L;
    /**
     * 创建日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime = new Date();
    /**
     * 修改日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime = new Date();
    /**
     * 创建人Id
     */
    private String createUser = SYSTEM_ADMIN;
    /**
     * 修改人id
     */
    private String modifyUser = SYSTEM_ADMIN;
    /**
     * 数据是否有效
     */
    private Boolean flag = false;
    /**
     * 是否删除
     */
    private Boolean  remove= false;
}
