package com.imooc.dao;

import com.imooc.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    //分页查询根据买家openid,主订单
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);
}
