package com.imooc.dao;

import com.imooc.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
商品
 */
public interface ProductDao extends JpaRepository<ProductInfo, String> {
    //查上架商品
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
