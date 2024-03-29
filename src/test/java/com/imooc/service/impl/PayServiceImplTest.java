package com.imooc.service.impl;

import com.imooc.Dto.OrderDto;
import com.imooc.service.OrderService;
import com.imooc.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {
    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;
    @Test
    public void create(){
        payService.create(orderService.findOne("15436743625822810"));
    }

    @Test
    public void refund( ){
        OrderDto orderDto = orderService.findOne("15436743625822810");
        payService.refund(orderDto);

    }
}