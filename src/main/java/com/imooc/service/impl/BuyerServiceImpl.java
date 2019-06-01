package com.imooc.service.impl;

import com.imooc.Dto.OrderDto;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
        OrderDto orderDto = check(openid, orderId);
        return orderDto;
    }

    @Override
    public OrderDto cancleOrder(String openid, String orderId) {
        return null;
    }


    public OrderDto check(String openid, String orderId) {
        OrderDto orderDto = orderService.findOne(orderId);
        //判断是否是自己的订单
        if (!orderDto.getBuyerOpenid().equals(openid)) {
            log.error("【查询订单】微信名不一致");
            throw new SellException(ResultEnum.PARAM_ERROR);

        }
        return orderDto;
    }
}
