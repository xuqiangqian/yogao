package com.yogo.fm.service.operation.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.common.utils.FmPageUtils;
import com.yogo.fm.mapper.operation.content.IOrderMapper;
import com.yogo.fm.model.operation.content.OrderEntity;
import com.yogo.fm.service.operation.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author zhaoleilei
 * @Description
 * @date 2018-05-28
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final IOrderMapper orderRepository;

    @Autowired
    public OrderServiceImpl(IOrderMapper orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public FmPage<OrderEntity> getOrders(OrderEntity orderEntity, FmPage<OrderEntity> page) throws FmException {
        if (page == null) {
            throw FmException.error("参数错误");
        }

        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<OrderEntity> orders = orderRepository.getOrders(orderEntity);
        FmPageUtils.fmPageHandler(page, new PageInfo<>(orders));
        return page;
    }
}
