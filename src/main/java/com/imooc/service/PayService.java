package com.imooc.service;

import com.imooc.Dto.OrderDto;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
*@Author:ma
*@Data:11:56 2018/11/29
 * 发起支付service
*/
public interface PayService {
    PayResponse create(OrderDto orderDto);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDto orderDto);
}
