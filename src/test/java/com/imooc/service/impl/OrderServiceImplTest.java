package com.imooc.service.impl;

import com.imooc.Dto.OrderDto;
import com.imooc.dataobject.OrderDetail;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private final String openid = "0.0";
    private final String orderid = "1315";

    @Test
    public void creat() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("王子");
        orderDto.setBuyerAddress("北京");
        orderDto.setBuyerOpenid(openid);
        orderDto.setBuyerPhone("139328");
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);

        orderDto.setOrderDetailList(orderDetailList);
        OrderDto result = orderService.creat(orderDto);
        log.info("【创建订单】 result={}" + result);

    }

    @Test
    public void findOne() {
        OrderDto orderDto = orderService.findOne("15424246880318819");
        log.info("【查询订单】 result={}" + orderDto);
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderDto> result = orderService.findList(openid, request);
        Assert.assertNotEquals(0, result.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDto orderDto = orderService.findOne(orderid);
        OrderDto result = orderService.cancel(orderDto);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());

    }

    @Test
    public void finish() {
        OrderDto orderDto = orderService.findOne("15424246880318819");
        OrderDto result = orderService.finish(orderDto);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());

    }

    @Test
    public void paid() {
        OrderDto orderDto = orderService.findOne("15424246880318819");
        OrderDto result = orderService.paid(orderDto);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

    @Test
    public void list(){
        PageRequest pageRequest=new PageRequest(0,2);
        Page<OrderDto> list = orderService.findList(pageRequest);
        Assert.assertTrue("查询所有订单",list.getTotalElements()>0);
    }
}