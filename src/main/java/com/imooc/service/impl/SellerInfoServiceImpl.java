package com.imooc.service.impl;

import com.imooc.dao.SellerInfoDao;
import com.imooc.dataobject.SellerInfo;
import com.imooc.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {
    @Autowired
    private SellerInfoDao sellerInfoDao;
    @Override
    public SellerInfo findByOpenid(String openid) {
        return sellerInfoDao.findByOpenid(openid);
    }
}
