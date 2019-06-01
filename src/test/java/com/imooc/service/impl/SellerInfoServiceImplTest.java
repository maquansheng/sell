package com.imooc.service.impl;

import com.imooc.service.SellerInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoServiceImplTest {

    private static  final String openid="as";
    @Autowired
    private SellerInfoService sellerInfoService;
    @Test
    public void test(){
        System.out.print(sellerInfoService.findByOpenid("as"));
    }

}