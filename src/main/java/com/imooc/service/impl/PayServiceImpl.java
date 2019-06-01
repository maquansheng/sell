package com.imooc.service.impl;

import com.imooc.Dto.OrderDto;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import com.imooc.service.PayService;
import com.imooc.utils.JsonUtil;
import com.imooc.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
*@Author:ma
*@Data:16:29 2018/11/29
 * 发起支付service层
*/
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    @Autowired
    private BestPayService bestPayService;
    @Autowired
    private OrderService orderService;

    private static String orderName="微信支付测试订单";
    //发起支付
    @Override
    public PayResponse create(OrderDto orderDto) {
        //使用支付sdk，先引入依赖再使用
        //BestPayServiceImpl bestPayService=new BestPayServiceImpl();
        //bestPayService.setWxPayH5Config();
       // bestPayService.pay()
        PayRequest request=new PayRequest();
        request.setOpenid(orderDto.getBuyerOpenid());
        request.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        request.setOrderId(orderDto.getOrderId());
        request.setOrderName(orderName);
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("request={}",JsonUtil.toJson(request));
        PayResponse response = bestPayService.pay(request);
        log.info("response={}",JsonUtil.toJson(response));
        return response;
    }
    //异步通知
    @Override
    public PayResponse notify(String notifyData) {
        PayResponse response = bestPayService.asyncNotify(notifyData);
        log.info("异步通知,response={}",response);

        //查询订单
        OrderDto orderDto = orderService.findOne(response.getOrderId());
        //判断订单是否存在
        if (orderDto==null){
            log.error("【微信支付】异步通知，订单不存在 orderId={}",response.getOrderId());
            throw new SellException(ResultEnum.ORDERMASTER_NOT_EXIST);
        }
        //判断金额是否一致
        if (!MathUtil.equals(response.getOrderAmount(),orderDto.getOrderAmount().doubleValue())){
            log.error("【异步通知】金额不一致，");
        }
        //修改订单状态
        orderService.paid(orderDto);

        return response;
    }
    //退款
    @Override
    public RefundResponse refund(OrderDto orderDto) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDto.getOrderId());
        refundRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】request={}", JsonUtil.toJson(refundRequest));

        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】response={}", JsonUtil.toJson(refundResponse));

        return refundResponse;
    }
}
