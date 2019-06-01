package com.imooc.dao;

import com.imooc.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailReponsitoryTest {
    @Autowired
    private OrderDetailReponsitory orderDetailReponsitory;

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1");
        orderDetail.setOrderId("1");
        orderDetail.setProductId("3");
        orderDetail.setProductName("钢笔");
        orderDetail.setProductPrice(new BigDecimal(32));
        orderDetail.setProductQuantity(10);
        orderDetail.setProductIcon("http://");
        orderDetailReponsitory.save(orderDetail);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> result = orderDetailReponsitory.findByOrderId("1");
    }

}