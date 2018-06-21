package com.yogo.fm.mapper.operation.content;

import com.yogo.fm.mapper.IBaseMapper;
import com.yogo.fm.model.operation.content.OrderEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhaoleilei
 * @Description
 * @date 2018-05-28
 */
public interface IOrderMapper extends IBaseMapper<OrderEntity> {
    /**
     * 获取条件获取订单列表
     *
     * @param oe
     * @return
     */
    List<OrderEntity> getOrders(@Param("oe") OrderEntity oe);
}
