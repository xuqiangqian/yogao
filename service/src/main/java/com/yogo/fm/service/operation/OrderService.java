package com.yogo.fm.service.operation;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.model.operation.content.OrderEntity;

/**
 * @author zhaoleilei
 * @Description
 * @date 2018-05-28
 */
public interface OrderService {

    /**
     * 获取条件获取订单列表
     *
     * @param orderEntity
     * @param page
     * @return
     * @throws FmException
     */
    FmPage<OrderEntity> getOrders(OrderEntity orderEntity, FmPage<OrderEntity> page) throws FmException;
}
