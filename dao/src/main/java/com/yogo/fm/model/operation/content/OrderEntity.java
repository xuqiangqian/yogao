package com.yogo.fm.model.operation.content;

import com.yogo.fm.model.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhaoleilei
 * @Description
 * @date 2018-05-28
 */
@Data
public class OrderEntity extends BaseEntity {

    /**
     * 订单编号
     */
    private String orderNum;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 订单金额
     */
    private BigDecimal money;

    /**
     * 支付方式(微信，支付宝)
     */
    private String paymentMethod;

    /**
     * 订单状态 true：1，false：0
     */
    private Boolean orderStatus = true;

    /**
     * 查询时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
