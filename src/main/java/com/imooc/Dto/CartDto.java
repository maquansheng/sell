package com.imooc.Dto;

import lombok.Data;

/**
 * @Author:ma
 * @Data:10:03 2018/11/17
 * 购物车
 */
@Data
public class CartDto {
    //商品id
    private String productId;
    //商品数量
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
