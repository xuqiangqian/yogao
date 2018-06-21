package com.yogo.fm.admin.controller.operation;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.response.FmResponse;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.model.operation.content.OrderEntity;
import com.yogo.fm.service.operation.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiqiang
 * @Description 订单管理
 * @date 2018-05-18
 */
@RestController
@RequestMapping("/order")
public class OrdersController {

    private final OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value="根据条件获取订单列表")
    @GetMapping("/list")
    public FmResponse<FmPage<OrderEntity>> getOrderByUserAccount(OrderEntity orderEntity, FmPage<OrderEntity> fmPage) throws FmException {
        // TODO: 2018/5/29 做订单管理时，需在此方法的SQL中补全条件
        return FmResponse.ok("查询订单列表成功", orderService.getOrders(orderEntity, fmPage));
    }
}
