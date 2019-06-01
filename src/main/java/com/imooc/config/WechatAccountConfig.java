package com.imooc.config;

import jdk.nashorn.internal.ir.debug.PrintVisitor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
*@Author:ma
*@Data:21:58 2018/11/27
 * 读取配置文件参数
*/
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    //公众号appid
    private String mpAppId;
    //公众号appsScret
    private String mpAppSecret;
    //开放平台id
    private String oppenAppId;
    //开放平台密钥
    private String oppenAppSecret;
    //商户号
    private String mchId;
    //商户秘钥
    private String mchKey;
    //商户证书路径
    private String keyPath;
    //微信异步支付通知地址
    private String notifyUrl;



}
