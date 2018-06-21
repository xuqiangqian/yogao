package com.yogo.fm.model.operation.content;

import com.alibaba.fastjson.annotation.JSONField;
import com.yogo.fm.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author qiqiang
 * @Description 推送消息
 * @date 2018-05-26
 */
@Data
@ApiModel
public class MessageEntity extends BaseEntity {
    /**
     * 消息标题
     */
    @ApiModelProperty(value = "消息标题")
    private String title;
    /**
     * 接收人数s
     */
    private Long receiveNumber;
    /**
     * 消息类型
     */
    private String type;
    /**
     * 消息类型状态，0 App推送，1 站内信，2 短信
     */
    @ApiModelProperty
    private Integer typeCode;
    /**
     * 发送时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;
    /**
     * 发送状态,0未发送，1已发送
     */
    private Boolean status;
    /**
     * 发送人
     */
    private Long sendUser;
    /**
     * 消息内容
     */
    private String content;


    /**
     * 查询条件
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty
    private Date endTime;
}
