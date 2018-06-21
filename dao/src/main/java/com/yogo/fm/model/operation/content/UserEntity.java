package com.yogo.fm.model.operation.content;

import com.alibaba.fastjson.annotation.JSONField;
import com.yogo.fm.model.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author qiqiang
 * @Description app用户账号
 * @date 2018-05-18
 */
@Data
public class UserEntity extends BaseEntity {

    /**
     * 用户账号
     */
    private String username;

    /**
     * 密码
     */
    @JSONField(serialize = false)
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String mobileNum;

    /**
     * 注册渠道
     */
    private String registrationChannel;

    /**
     * 加密盐
     */
    @JSONField(serialize = false)
    private String salt;

    /**
     * 是否允许登录pc商铺端
     */
    private Boolean pcStore = false;

    /**
     * token
     */
    @JSONField(serialize = false)
    private String token;

    /**
     * 友盟deviceToken
     */
    @JSONField(serialize = false)
    private String deviceToken;

    /**
     * userInfo表的id
     */
    private Long userInfoId;


    private Userinfo userinfo;

    /**
     * 查询时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
