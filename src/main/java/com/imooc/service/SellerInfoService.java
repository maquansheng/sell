package com.imooc.service;

import com.imooc.dataobject.SellerInfo;

public interface SellerInfoService {
    SellerInfo findByOpenid(String openid);
}
