package com.imooc.controller;

import com.imooc.Dto.OrderDto;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
*@Author:ma
*@Data:10:59 2018/12/4
 * 卖家端商品
*/
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellOrderController {
    @Autowired
    private OrderService orderService;
    //订单列表
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "2") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest=new PageRequest(page-1,size);
        Page<OrderDto> orderDtoPage = orderService.findList(pageRequest);
        map.put("orderDtoPage",orderDtoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);
    }
    /*
    取消订单
     */
     @GetMapping("/cancel")
    public ModelAndView cancle(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
         try {
             OrderDto orderDto = orderService.findOne(orderId);
             orderService.cancel(orderDto);
         }catch (SellException e){
             log.error("【取消订单错误】");
             map.put("msg",e.getMessage());
             map.put("url","/sell/seller/order/list");
             return new ModelAndView("common/error",map);
         }
         map.put("msg",ResultEnum.ORDER_SUCCESS.getMsg());
         map.put("url","/sell/seller/order/list");
         return new ModelAndView("common/success",map);
     }
     /*
     订单详情
      */
     @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
         OrderDto orderDto=new OrderDto();
         try {
             orderDto=orderService.findOne(orderId);
         }catch (SellException e){
               log.error("【订单详情异常】");
               map.put("msg",e.getMessage());
               map.put("url","/sell/seller/order/list");
               return new ModelAndView("common/error",map);
         }
         map.put("orderDto",orderDto);
         return  new ModelAndView("order/detail",map);
     }
     /*
     完结订单
      */
     @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
         try {
             OrderDto orderDto = orderService.findOne(orderId);
             orderService.finish(orderDto);
         }catch (SellException e){
             log.error("【完结订单】异常");
             map.put("msg",e.getMessage());
             map.put("url","/sell/seller/order/list");
             return new ModelAndView("common/error");
         }
         map.put("msg",ResultEnum.ORDER_FINISHED);
         map.put("url","/sell/seller/order/list");
         return new ModelAndView("common/success");

     }
}
