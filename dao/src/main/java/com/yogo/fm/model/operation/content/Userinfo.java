package com.yogo.fm.model.operation.content;

import com.alibaba.fastjson.annotation.JSONField;
import com.yogo.fm.model.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author qiqiang
 * @Description app用户信息
 * @date 2018-05-18
 */
@Data
public class Userinfo extends BaseEntity {

    /**
     * 账号id
     */
    private String accountId;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户性别 female：女，male：男
     */
    private String gender;

    /**
     * 生日
     */
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 地址
     */
    private String address;

    /**
     * 职业
     */
    private String career;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 登录手机类型,0:Android,1:IOS
     */
    private Integer phoneType;

}
