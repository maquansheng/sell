package com.imooc.service;

import com.imooc.Dto.CartDto;
import com.imooc.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductInfo findOne(String productId);

    //查询所有在架商品
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increase(List<CartDto> cartDtoList);

    //减库存
    void decrease(List<CartDto> cartDtoList);

    //上架
    ProductInfo on_sell(String productId);

    //下架
    ProductInfo off_sell(String productId);
}
