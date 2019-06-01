package com.imooc.service;

import com.imooc.Dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author:ma
 * @Data:17:11 2018/11/15
 */
public interface OrderService {
    //创建订单
    OrderDto creat(OrderDto orderDto);
    //查询单个订单
    OrderDto findOne(String orderId);
    //查询订单列表
    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);
    //取消订单
    OrderDto cancel(OrderDto orderDto);
    //完结订单
    OrderDto finish(OrderDto orderDto);
    //支付订单
    OrderDto paid(OrderDto orderDto);

    //卖家端，查询订单列表
    Page<OrderDto> findList(Pageable pageable);

}
