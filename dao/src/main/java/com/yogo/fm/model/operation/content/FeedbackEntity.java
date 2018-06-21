package com.yogo.fm.model.operation.content;

import com.alibaba.fastjson.annotation.JSONField;
import com.yogo.fm.model.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-30
 */
@Data
public class FeedbackEntity extends BaseEntity {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 反馈时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date feedbackTime;
    /**
     * 是否已经查看
     */
    private Boolean lookOver;
    /**
     * 详情
     */
    private String details;

    /**
     * 查询时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
