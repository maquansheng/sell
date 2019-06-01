package com.imooc.service;

import com.imooc.Dto.OrderDto;

public interface BuyerService {
    OrderDto findOrderOne(String openid, String orderId);

    OrderDto cancleOrder(String openid, String orderId);
}
