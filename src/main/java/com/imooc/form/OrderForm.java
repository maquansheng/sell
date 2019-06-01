package com.imooc.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class OrderForm {
    @NotEmpty
    //买家姓名
    private String name;
    @NotEmpty
    //买家电话
    private String phone;
    @NotEmpty
    //买家地址
    private String address;
    @NotEmpty
    //买家微信
    private String openid;
    @NotEmpty
    //购物车
    private String items;
}
