package com.imooc.controller;

import com.imooc.Dto.OrderDto;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import com.imooc.service.PayService;
import com.imooc.service.impl.PayServiceImpl;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
*@Author:ma
*@Data:10:18 2018/12/1
 * 发起支付
*/
@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayServiceImpl payService;
    //发起支付
    @GetMapping("/create")
    public ModelAndView pay(@RequestParam("orderId") String orderId,
                             @RequestParam("returnUrl") String returnUrl,
                             Map<String,Object> map){
        //1.查询订单
        OrderDto orderDto = orderService.findOne(orderId);
        if (orderDto==null){
            throw new SellException(ResultEnum.ORDERMASTER_NOT_EXIST);
        }
        //2.发起支付
        PayResponse response = payService.create(orderDto);
        map.put("returnUrl",returnUrl);
        map.put("payResponse",response);
        return new ModelAndView("create",map);
    }

    //微信异步通知
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);
        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }
}
