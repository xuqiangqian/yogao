package com.yogo.fm.model.system;

import com.alibaba.fastjson.annotation.JSONField;
import com.yogo.fm.annotation.ExcelFieldValue;
import com.yogo.fm.annotation.SolrField;
import com.yogo.fm.model.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author qiqiang
 * @Description PC账号后台
 * @date 2018-05-18
 */
@Data
public class AccountEntity extends BaseEntity{
    /**
     * 账号
     */
    @ExcelFieldValue("用户名")
    @SolrField
    private String username;
    /**
     * 姓名
     */
    @ExcelFieldValue("姓名")
    @SolrField
    private String name;
    /**
     * 联系方式
     */
    @ExcelFieldValue("联系方式")
    @SolrField
    private String mobile;
    /**
     * 密码
     */
    @JSONField(serialize = false)
    @ExcelFieldValue("密码")
    private String password;
    /**
     * 有效起始日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date effectiveStartDate;
    /**
     * 有效结束日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date effectiveEndDate;
    /**
     * 加密盐
     */
    @JSONField(serialize = false)
    private String salt;
    /**
     * token
     */
    @JSONField(serialize = false)
    private String token;
    /**
     * 管理员的角色
     */
    private Long roleId;
    @ExcelFieldValue("角色")
    @SolrField
    private String roleName;

    /**
     * 查询时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
