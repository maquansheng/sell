package com.imooc.dao;

import com.imooc.dataobject.SellerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoDaoTest {
    @Autowired
    private SellerInfoDao sellerInfoDao;
    @Test
    public void test(){
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setOpenid("as");
        sellerInfo.setPassword("123");
        sellerInfo.setId("123");
        sellerInfo.setUsername("ma");
        sellerInfoDao.save(sellerInfo);
    }

    @Test
    public void test2(){

    }

}