package com.imooc.service;

import com.imooc.Dto.OrderDto;

/**
*@Author:ma
*@Data:21:03 2018/12/23
 * 消息推送
 */
public interface PushMessage {
    //订单状态变更消息
    void orderStatus(OrderDto orderDto);
}
