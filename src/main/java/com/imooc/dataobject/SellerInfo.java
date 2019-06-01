package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
*@Author:ma
*@Data:22:12 2018/12/22
 * 卖家信息
*/
@Entity
@Data
public class SellerInfo {
    @Id
    private String id;
    private String username;
    private String password;
    private String openid;
}
